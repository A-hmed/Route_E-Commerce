package com.example.routee_commerce.data.model.response.product

import com.example.routee_commerce.data.model.response.category.CategoryDM
import com.google.gson.annotations.SerializedName

data class ProductDM(

    @field:SerializedName("sold")
	val sold: Int? = null,

    @field:SerializedName("images")
	val images: List<String?>? = null,

    @field:SerializedName("quantity")
	val quantity: Int? = null,

    @field:SerializedName("imageCover")
	val imageCover: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("title")
	val title: String? = null,

    @field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

    @field:SerializedName("ratingsAverage")
	val ratingsAverage: Double? = null,

    @field:SerializedName("createdAt")
	val createdAt: String? = null,

    @field:SerializedName("price")
	val price: Double? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("subcategory")
	val subcategory: List<CategoryDM?>? = null,

    @field:SerializedName("category")
	val category: CategoryDM? = null,

    @field:SerializedName("brand")
	val brand: CategoryDM? = null,

    @field:SerializedName("slug")
	val slug: String? = null,

    @field:SerializedName("updatedAt")
	val updatedAt: String? = null
)