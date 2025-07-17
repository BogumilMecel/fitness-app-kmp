package presentation.register

import androidx.lifecycle.viewModelScope
import components.TextFieldData
import domain.use_case.RegisterUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import navigation.presentation.Route
import presentation.base.BaseModel
import utils.flow.getText
import utils.flow.initTextField
import utils.flow.isNotError
import utils.handle

class RegisterScreenModel(private val registerUseCases: RegisterUseCases) : BaseModel() {

    val email = MutableStateFlow(TextFieldData())
    val username = MutableStateFlow(TextFieldData())
    val password = MutableStateFlow(TextFieldData())

    init {
        email.initTextField()
        username.initTextField()
        password.initTextField()
    }

    fun onRegisterButtonClicked() {
        viewModelScope.launch {
            registerUseCases.validateEmailUseCase(
                email = email.getText()
            ).handle(validationField = email)

            registerUseCases.validateUsernameUseCase(
                username = username.getText()
            ).handle(validationField = username)

            registerUseCases.validatePasswordUseCase(
                password = password.getText()
            ).handle(validationField = password)

            if (email.isNotError() && username.isNotError() && password.isNotError()) {
                requestRegister()
            }
        }
    }

    private suspend fun requestRegister() {
        registerUseCases.registerUserUseCase(
            email = email.getText(),
            password = password.getText(),
            username = username.getText()
        ).handle(
            onSuccess = {
                navigateTo(Route.BottomNavigation.Summary)
            }
        )
    }
}