package com.example.routee_commerce.domain.mappers

import com.example.routee_commerce.data.model.response.cart.CartItem
import com.example.routee_commerce.data.model.response.product.ProductDM
import com.example.routee_commerce.domain.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor(private val categoryMapper: CategoryMapper){
    fun toProduct(productDM: ProductDM): Product{
        return Product(id = productDM.id,
             sold = productDM.sold,
            images = productDM.images,
            quantity = productDM.quantity,
            imageCover = productDM.imageCover,
            description = productDM.description,
            title = productDM.title,
            ratingsAverage = productDM.ratingsAverage,
            ratingsQuantity = productDM.ratingsQuantity,
            price = productDM.price,
            category = productDM.category?.let { categoryMapper.toCategory(it) },
            priceAfterDiscount = productDM.price);
    }

    fun mapCartToItemsToProducts(cartItems: List<CartItem>): List<Product>{
        return cartItems.map {
            Product(id = it.productDM?.id,
                sold = it.productDM?.sold,
                images = it.productDM?.images,
                quantity = it.productDM?.quantity,
                imageCover = it.productDM?.imageCover,
                description = it.productDM?.description,
                title = it.productDM?.title,
                ratingsAverage = it.productDM?.ratingsAverage,
                ratingsQuantity = it.productDM?.ratingsQuantity,
                price = it.productDM?.price,
                category = it.productDM?.category?.let { categoryMapper.toCategory(it) },
                priceAfterDiscount = it.productDM?.price,
                totalItemsInCart = it.count,
                totalPriceInCart = it.price);
        }
    }
}