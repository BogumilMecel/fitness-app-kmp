package presentation.login

import cafe.adriel.voyager.core.model.screenModelScope
import domain.use_case.LogInUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.BaseModel

class LoginScreenModel(private val logInUserUseCase: LogInUserUseCase): BaseModel(), KoinComponent {

    val state = MutableStateFlow(LoginState())

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
        screenModelScope.launch {
            logInUserUseCase(
                email = state.value.email,
                password = state.value.password
            ).handle(
                onSuccess = {

                }
            )
        }
    }

    fun onForgotPasswordClicked() {

    }
}