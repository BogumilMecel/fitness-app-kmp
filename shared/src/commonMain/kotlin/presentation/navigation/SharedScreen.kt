package presentation.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface SharedScreen : ScreenProvider {
    data object AuthNavigationScreen : SharedScreen
    data object TabNavigatorScreen : SharedScreen
}