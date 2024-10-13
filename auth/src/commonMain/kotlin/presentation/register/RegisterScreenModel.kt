package presentation.register

import cafe.adriel.voyager.core.model.screenModelScope
import components.TextFieldData
import domain.use_case.RegisterUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.base.BaseModel
import presentation.navigation.SharedScreen

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
        screenModelScope.launch {
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
                navigateToSharedScreen(SharedScreen.TabNavigatorScreen)
            }
        )
    }
}