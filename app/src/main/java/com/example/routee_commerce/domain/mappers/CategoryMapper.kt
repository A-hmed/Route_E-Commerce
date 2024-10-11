package com.example.routee_commerce.domain.mappers

import com.example.routee_commerce.data.model.response.category.CategoryDM
import com.example.routee_commerce.domain.model.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor(){
    fun toCategory(categoryDM: CategoryDM): Category{
        return Category(id = categoryDM.id,
            name = categoryDM.name,
            image = categoryDM.image);
    }
}