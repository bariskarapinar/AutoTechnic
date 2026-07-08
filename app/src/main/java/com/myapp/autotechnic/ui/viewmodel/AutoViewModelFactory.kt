package com.myapp.autotechnic.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.autotechnic.data.repository.AutoRepository

class AutoViewModelFactory(private val repository: AutoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AutoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AutoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
