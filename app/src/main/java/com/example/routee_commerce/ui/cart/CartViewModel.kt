package com.example.routee_commerce.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.repos.CartRepo
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepo: CartRepo
) : ViewModel() {
    private var _cart = MutableLiveData<Resource<Cart>>()
    val cart: LiveData<Resource<Cart>> = _cart

    fun loadCart() {
        viewModelScope.launch {
            _cart.postValue(Resource.Loading)
            when (val response = cartRepo.getCart()) {
                is ApiResult.Error -> _cart.postValue(Resource.Error(response.error))
                is ApiResult.Success -> _cart.postValue(Resource.Success(response.data))
            }
        }
    }

    fun addCartItem(productId: String) {
        viewModelScope.launch {
            _cart.postValue(Resource.Loading)
            when (val response = cartRepo.addCartEntry(productId)) {
                is ApiResult.Error -> _cart.postValue(Resource.Error(response.error))
                is ApiResult.Success -> _cart.postValue(Resource.Success(response.data))
            }
        }
    }

    fun removeCartItem(productId: String) {
        viewModelScope.launch {
            _cart.postValue(Resource.Loading)
            when (val response = cartRepo.removeCartEntry(productId)) {
                is ApiResult.Error -> _cart.postValue(Resource.Error(response.error))
                is ApiResult.Success -> _cart.postValue(Resource.Success(response.data))
            }
        }
    }

    fun updateCartQuantity(productId: String, quantity: Int) {
        viewModelScope.launch {
            _cart.postValue(Resource.Loading)
            when (val response = cartRepo.updateCartEntryAmount(productId, quantity)) {
                is ApiResult.Error -> _cart.postValue(Resource.Error(response.error))
                is ApiResult.Success -> _cart.postValue(Resource.Success(response.data))
            }
        }
    }
}