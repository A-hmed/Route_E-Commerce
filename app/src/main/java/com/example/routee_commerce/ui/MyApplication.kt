package com.example.routee_commerce.ui

import android.app.Application
import com.example.routee_commerce.utils.InternetConnectionChecker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        //todo: Remove this line with something better
        InternetConnectionChecker.context = this
    }
}