package com.example.routee_commerce.data.model.response.cart

import com.example.routee_commerce.data.model.response.product.ProductDM
import com.google.gson.annotations.SerializedName

data class CartItem(

	@field:SerializedName("product")
	val productDM: ProductDM? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null
)