package com.example.routee_commerce.data.utils.local_storage

import android.content.Context
import android.content.SharedPreferences
import com.example.routee_commerce.data.model.response.User
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorageImpl @Inject constructor(@ApplicationContext private val context: Context) :
    LocalStorage {
    companion object {
        val sharedPrefName = "SharedPref"
    }

    override fun saveUser(user: User) {
        val sharedPreferences =
            context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(user)
        sharedPreferences.edit().putString("user", json).apply()
    }

    override fun getUser(): User? {
        val sharedPreferences =
            context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("user", null) ?: return null
        val gson = Gson()
        return gson.fromJson(json, User::class.java)

    }

    override fun saveToken(token: String) {
        val sharedPreferences =
            context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()

    }

    override fun getToken(): String? {
        val sharedPreferences =
            context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }

}