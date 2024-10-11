package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.domain.repos.MainRepo
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MainRepo
): ViewModel(){
    private val _categories = MutableLiveData<Resource<List<Category>>>()
    val categories: LiveData<Resource<List<Category>>> = _categories

    private val _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> = _products

    init {
        loadProducts()
        loadCategories()
    }
    private fun loadProducts(){
        _products.value = Resource.Loading
        viewModelScope.launch {
            when(val result = repo.getProducts()){
                is ApiResult.Error -> _products.value = Resource.Error(result.error)
                is ApiResult.Success -> _products.value = Resource.Success(result.data)
            }
        }
    }
    private fun loadCategories(){
        _categories.value = Resource.Loading
        viewModelScope.launch {
            when(val result = repo.getCategories()){
                is ApiResult.Error -> _categories.value = Resource.Error(result.error)
                is ApiResult.Success -> _categories.value = Resource.Success(result.data)
            }
        }
    }
}