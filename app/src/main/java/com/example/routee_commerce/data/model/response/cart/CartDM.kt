package com.example.routee_commerce.data.model.response.cart

import com.google.gson.annotations.SerializedName

data class CartDM(

	@field:SerializedName("cartOwner")
	val cartOwner: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("totalCartPrice")
	val totalCartPrice: Double? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("products")
	val cartItems: List<CartItem>? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)