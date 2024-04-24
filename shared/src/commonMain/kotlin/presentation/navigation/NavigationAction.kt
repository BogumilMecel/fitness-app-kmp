package presentation.navigation

import cafe.adriel.voyager.core.screen.Screen

sealed class NavigationAction(open val withPopUp: Boolean) {
    data class ToScreen(
        override val withPopUp: Boolean,
        val screen: Screen
    ) : NavigationAction(withPopUp)

    data class ToSharedScreen(
        val screen: SharedScreen
    ) : NavigationAction(withPopUp = true)

    data class Back(override val withPopUp: Boolean) : NavigationAction(withPopUp)
}