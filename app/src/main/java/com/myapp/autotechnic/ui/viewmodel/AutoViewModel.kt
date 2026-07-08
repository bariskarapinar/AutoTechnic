package com.myapp.autotechnic.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.autotechnic.data.repository.AutoRepository
import com.myapp.autotechnic.domain.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AutoViewModel(private val repository: AutoRepository) : ViewModel() {

    private val _brands = MutableStateFlow<List<Brand>>(emptyList())
    val brands: StateFlow<List<Brand>> = _brands.asStateFlow()

    private val _models = MutableStateFlow<List<CarModel>>(emptyList())
    val models: StateFlow<List<CarModel>> = _models.asStateFlow()

    private val _generations = MutableStateFlow<List<Generation>>(emptyList())
    val generations: StateFlow<List<Generation>> = _generations.asStateFlow()

    private val _selectedSpecs = MutableStateFlow<TechnicalSpecs?>(null)
    val selectedSpecs: StateFlow<TechnicalSpecs?> = _selectedSpecs.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadBrands()
    }

    private fun loadBrands() {
        viewModelScope.launch {
            _isLoading.value = true
            _brands.value = repository.getBrands().sortedBy { it.name }
            _isLoading.value = false
        }
    }

    fun selectBrand(brandId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _models.value = repository.getModels(brandId).sortedBy { it.name }
            _isLoading.value = false
        }
    }

    fun selectModel(brandId: String, modelId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _generations.value = repository.getGenerations(brandId, modelId)
            _isLoading.value = false
        }
    }

    fun selectGeneration(brandName: String, modelName: String, generationName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _selectedSpecs.value = repository.getSpecs(brandName, modelName, generationName)
            _isLoading.value = false
        }
    }
}
