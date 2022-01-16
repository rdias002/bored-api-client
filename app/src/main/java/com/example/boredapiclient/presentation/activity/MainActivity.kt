package com.example.boredapiclient.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.boredapiclient.databinding.ActivityMainBinding
import com.example.boredapiclient.presentation.viewmodel.HomeActivityViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeActivityViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        collectFlows()
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiEventsSharedFlow.collect {
                        handleUiEvent(it)
                    }
                }
            }
        }
    }

    private fun handleUiEvent(uiEvent: HomeActivityViewModel.UiEvents) {
        when (uiEvent) {
            is HomeActivityViewModel.UiEvents.ShowToast -> {
                Toast.makeText(this, uiEvent.message, uiEvent.length).show()
            }
            is HomeActivityViewModel.UiEvents.ShowSnackBar -> {
                Snackbar.make(binding.root, uiEvent.message, uiEvent.length).show()
            }
        }
    }
}