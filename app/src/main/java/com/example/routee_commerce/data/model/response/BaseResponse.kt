package com.example.routee_commerce.data.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @field:SerializedName("metadata")
	val metadata: Metadata? = null,

    @field:SerializedName("data")
	val data: T? = null,

    @field:SerializedName("results")
	val results: Int? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
    )