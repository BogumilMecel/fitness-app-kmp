package com.gmail.bogumilmecel2

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.core.registry.ScreenRegistry
import di.startDi
import main_screen.presentation.TabNavigatorScreen
import org.koin.dsl.module
import presentation.navigation.SharedScreen
import presentation.navigation_screen.AuthNavigationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ScreenRegistry {
            register<SharedScreen.AuthNavigationScreen> {
                AuthNavigationScreen()
            }
            register<SharedScreen.TabNavigatorScreen> {
                TabNavigatorScreen()
            }
        }

        startDi(nativeModule = module {  })

        setContent {
            App()
        }
    }
}