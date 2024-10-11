package com.example.routee_commerce.data.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusMsg")
	val statusMsg: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
)