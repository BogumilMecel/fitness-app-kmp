package presentation.login

import cafe.adriel.voyager.core.model.screenModelScope
import components.TextFieldData
import domain.use_case.LoginUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import presentation.BaseModel

class LoginScreenModel(private val loginUseCases: LoginUseCases) : BaseModel() {

    private val email = MutableStateFlow(TextFieldData())
    private val password = MutableStateFlow(TextFieldData())

    val state = combine(email, password) { email, password ->
        LoginState(email = email, password = password)
    }.collectInScreenModel(LoginState())

    fun onEmailChanged(value: String) = email.setText(value)

    fun onPasswordChanged(value: String) = password.setText(value)

    fun onForgotPasswordClicked() {

    }

    fun onLoginButtonClicked() {
        loginUseCases.validateEmailUseCase(
            email = email.getText()
        ).handle(validationField = email)

        loginUseCases.validatePasswordUseCase(
            password = password.getText()
        ).handle(validationField = password)

        if (!email.isError() && !password.isError()) {
            requestLogin()
        }
    }

    private fun requestLogin() {
        screenModelScope.launch {
            loginUseCases.logInUserUseCase(
                email = email.getText(),
                password = password.getText()
            ).handle(
                onSuccess = {

                }
            )
        }
    }
}