package com.gmail.bogumilmecel2

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import di.getAndroidSharedModule
import di.startDi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startDi(nativeModule = getAndroidSharedModule(context = this))

        setContent {
            App()
        }
    }
}