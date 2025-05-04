package com.rayman.myfruits.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rayman.myfruits.data.model.ApiResponse
import com.rayman.myfruits.data.model.Attribute
import com.rayman.myfruits.data.model.Nutrients
import com.rayman.myfruits.repository.FruitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val repository: FruitRepository
) : ViewModel() {
    private val _fruits = MutableStateFlow<ApiResponse>(ApiResponse.Loading)
    val fruits: StateFlow<ApiResponse> = _fruits

    fun getAllFruits() {
        viewModelScope.launch {
            _fruits.emit(ApiResponse.Loading)
            repository.getAllFruits().collect { response ->
                _fruits.emit(response)
            }
        }
    }

    fun getFruitByAttribute(attribute: Attribute,attributeValue: String){
        viewModelScope.launch {
            _fruits.emit(ApiResponse.Loading)
            repository.getFruitByAttribute(attribute,attributeValue).collect { response ->
                _fruits.emit(response)
            }
        }
    }

    fun getFruitByNutrition(nutrient: Nutrients, min: Double?, max: Double?) {
        viewModelScope.launch {
            _fruits.emit(ApiResponse.Loading)
            repository.getFruitByNutrient(nutrient, min, max).collect { response ->
                _fruits.emit(response)
            }
        }
    }
    
    init {
        viewModelScope.launch {
            getAllFruits()
        }
    }
}