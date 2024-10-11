package com.example.routee_commerce.data.utils.local_storage

import com.example.routee_commerce.data.model.response.User

interface LocalStorage {
    fun saveUser(user: User)
    fun getUser(): User?

    fun saveToken(token: String)
    fun getToken(): String?
}