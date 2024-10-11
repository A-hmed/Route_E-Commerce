package com.example.routee_commerce.domain.model

import android.os.Parcelable
import com.example.routee_commerce.domain.model.Category
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val sold: Int? = null,
    val images: List<String?>? = null,
    val quantity: Int? = null,
    val imageCover: String? = null,
    val description: String? = null,
    val title: String? = null,
    val ratingsQuantity: Int? = null,
    val ratingsAverage: Double? = null,
    val price: Double? = null,
    val id: String? = null,
    val category: Category? = null,
    val priceAfterDiscount: Double? = null,
    val totalItemsInCart: Int? = null,
    val totalPriceInCart: Double? = null,
) : Parcelable {
	companion object {
		const val PRODUCT = "product"
	}
}
