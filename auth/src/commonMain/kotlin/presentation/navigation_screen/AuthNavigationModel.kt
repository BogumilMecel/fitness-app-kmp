package presentation.navigation_screen

import navigation.presentation.Route
import presentation.base.BaseModel

class AuthNavigationModel: BaseModel() {

    fun onSignInWithEmailClicked() {
        navigateTo(Route.Login)
    }

    fun onSignInWithGoogleClicked() {

    }

    fun onSignUpWithEmailClicked() {
        navigateTo(Route.Register)
    }
}