package com.example.routee_commerce.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subcategory(
    val createdAt: String? = null,
    val name: String? = null,
    val id: String? = null,
    val category: String? = null,
    val slug: String? = null,
    val updatedAt: String? = null
) : Parcelable
