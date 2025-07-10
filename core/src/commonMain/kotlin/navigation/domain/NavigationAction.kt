package navigation.domain

import navigation.presentation.Route

sealed class NavigationAction(open val withPopUp: Boolean) {
    data class ToScreen(
        override val withPopUp: Boolean,
        val route: Route
    ) : NavigationAction(withPopUp)

    data class Back(override val withPopUp: Boolean) : NavigationAction(withPopUp)
}