package com.example.boredapiclient.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boredapiclient.core.utils.Resource
import com.example.boredapiclient.domain.model.ActivityModel
import com.example.boredapiclient.domain.usecase.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val getRandomActivityUseCaseUseCase: GetRandomActivityUseCase,
    private val saveActivityUseCase: SaveActivityUseCase,
    private val getSavedActivitiesUseCase: GetSavedActivitiesUseCase,
    private val markActivityAsDoneUseCase: MarkActivityAsDoneUseCase,
    private val deleteSavedActivityUseCase: DeleteSavedActivityUseCase
) : ViewModel() {

    private val _uiEventsSharedFlow = MutableSharedFlow<UiEvents>()
    val uiEventsSharedFlow: SharedFlow<UiEvents> = _uiEventsSharedFlow

    val textViewState = MutableStateFlow("")

    private lateinit var activity: ActivityModel

    fun getRandomActivity() {
        viewModelScope.launch {
            getRandomActivityUseCaseUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        textViewState.value = it.data.toString()
                        it.data?.let { a -> activity = a }
                    }
                    is Resource.Error -> {
                        _uiEventsSharedFlow.emit(
                            UiEvents.ShowSnackBar(
                                it.message ?: "Something went wrong"
                            )
                        )
                    }
                }
            }
        }
    }

    fun saveActivity() {
        viewModelScope.launch {
            if (::activity.isInitialized) {
                saveActivityUseCase.invoke(activity).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            _uiEventsSharedFlow.emit(
                                UiEvents.ShowToast(it.message ?: "Saved")
                            )
                        }
                        is Resource.Error -> {
                            _uiEventsSharedFlow.emit(
                                UiEvents.ShowToast(
                                    it.message ?: "Could not save activity"
                                )
                            )
                        }
                    }
                }
            } else {
                _uiEventsSharedFlow.emit(UiEvents.ShowToast("Activity not found"))
            }
        }
    }

    sealed class UiEvents {
        class ShowToast(val message: String, val length: Int = Toast.LENGTH_SHORT) : UiEvents()
        class ShowSnackBar(val message: String, val length: Int = Snackbar.LENGTH_SHORT) :
            UiEvents()
    }
}
