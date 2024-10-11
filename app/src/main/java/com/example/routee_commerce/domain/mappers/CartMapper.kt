package com.example.routee_commerce.domain.mappers

import com.example.routee_commerce.data.model.response.cart.CartDM
import com.example.routee_commerce.data.model.response.cart.CartItem
import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.model.Product
import javax.inject.Inject

class CartMapper @Inject constructor(
    private var productMapper: ProductMapper
) {
    fun toCart(cartDM: CartDM): Cart {
        val products: List<Product>? = cartDM.cartItems?.let {
            productMapper.mapCartToItemsToProducts(it)
        }
        return Cart(
            totalCartPrice = cartDM.totalCartPrice ?: 0.0,
            productsList = products ?: emptyList()
        )
    }


}