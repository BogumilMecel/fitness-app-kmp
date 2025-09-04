package navigation.domain

import navigation.presentation.Route

sealed interface NavigationAction {
    data class ToScreen(
        val popUpParams: PopUpParams? = null,
        val route: Route,
    ) : NavigationAction

    data class Back(val withPopUp: Boolean) : NavigationAction
}

data class PopUpParams(
    val popUpTo: Route,
    val inclusive: Boolean = true,
)