package com.example.routee_commerce.domain.model

import com.example.routee_commerce.data.model.response.cart.CartDM

data class Cart(
    var productsList: List<Product>,
    var totalCartPrice: Double,
)
