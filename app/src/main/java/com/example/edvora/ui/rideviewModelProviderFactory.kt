package com.example.edvora.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.edvora.RideRepository

class rideviewModelProviderFactory(val repo:RideRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel(repo) as T
    }

}