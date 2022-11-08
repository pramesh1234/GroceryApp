package com.example.groceryapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GroceryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}