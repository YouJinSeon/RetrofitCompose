package com.teddy.example.compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeApplication: Application(){

    companion object {
        private lateinit var application: ComposeApplication
        fun getInstance(): ComposeApplication = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}