package presentation.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import presentation.register.RegisterScreen
import utils.BaseModel

class LoginScreenModel: BaseModel() {

    val state = MutableStateFlow(LoginState())

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun onEmailChanged(value: String) {
        state.update {
            it.copy(email = value)
        }
    }

    fun onPasswordChanged(value: String) {
        state.update {
            it.copy(password = value)
        }
    }

    fun onLoginButtonClicked() {

    }

    fun onForgotPasswordClicked() {

    }
}