package presentation.login

import cafe.adriel.voyager.core.model.screenModelScope
import components.TextFieldData
import domain.use_case.LoginUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.BaseModel
import presentation.navigation.SharedScreen

class LoginScreenModel(private val loginUseCases: LoginUseCases) : BaseModel() {

    val email = MutableStateFlow(TextFieldData())
    val password = MutableStateFlow(TextFieldData())
    val passwordVisible = MutableStateFlow(true)
    val buttonEnabled = MutableStateFlow(true)

    init {
        email.initTextField()
        password.initTextField()
    }

    fun onForgotPasswordClicked() {
        // TODO: Navigate to forgot password activity
    }

    fun onShowPasswordClicked() {
        passwordVisible.value = !passwordVisible.value
    }

    fun onLoginButtonClicked() {
        loginUseCases.validateEmailUseCase(
            email = email.getText()
        ).handle(validationField = email)

        loginUseCases.validatePasswordUseCase(
            password = password.getText()
        ).handle(validationField = password)

        if (email.isNotError() && password.isNotError()) {
            requestLogin()
        }
    }

    private fun requestLogin() {
        buttonEnabled.value = false
        screenModelScope.launch {
            loginUseCases.logInUserUseCase(
                email = email.getText(),
                password = password.getText()
            ).handle(
                onSuccess = {
                    navigateToSharedScreen(SharedScreen.TabNavigatorScreen)
                },
                finally = {
                    buttonEnabled.value = true
                }
            )
        }
    }
}