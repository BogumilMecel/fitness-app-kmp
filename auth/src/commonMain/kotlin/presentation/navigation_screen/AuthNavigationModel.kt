package presentation.navigation_screen

import presentation.login.LoginScreen
import presentation.register.RegisterScreen
import presentation.base.BaseModel

class AuthNavigationModel: BaseModel() {

    fun onSignInWithEmailClicked() {
        navigateTo(LoginScreen())
    }

    fun onSignInWithGoogleClicked() {

    }

    fun onSignUpWithEmailClicked() {
        navigateTo(RegisterScreen())
    }
}