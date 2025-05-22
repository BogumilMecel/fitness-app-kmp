package presentation.login

import androidx.lifecycle.viewModelScope
import components.TextFieldData
import domain.use_case.LoginUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import navigation.presentation.Route
import presentation.base.BaseModel

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
        viewModelScope.launch {
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
    }

    private suspend fun requestLogin() {
        buttonEnabled.value = false
        loginUseCases.logInUserUseCase(
            email = email.getText(),
            password = password.getText()
        ).handle(
            onSuccess = {
                navigateTo(Route.BottomNavigation.Summary)
            },
            finally = {
                buttonEnabled.value = true
            }
        )
    }
}