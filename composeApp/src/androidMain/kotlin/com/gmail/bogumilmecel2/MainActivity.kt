package com.gmail.bogumilmecel2

import App
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import di.getAndroidSharedModule
import di.startDi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startDi(nativeModule = getAndroidSharedModule(context = this))

        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(
                    Color.TRANSPARENT
                ),
                navigationBarStyle = SystemBarStyle.dark(
                    Color.TRANSPARENT,
                )
            )

            App()
        }
    }
}