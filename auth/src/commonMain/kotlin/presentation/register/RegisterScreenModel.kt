package presentation.register

import components.TextFieldData
import kotlinx.coroutines.flow.MutableStateFlow
import presentation.BaseModel

class RegisterScreenModel: BaseModel() {

    val email = MutableStateFlow(TextFieldData())
    val username = MutableStateFlow(TextFieldData())
    val password = MutableStateFlow(TextFieldData())
    val confirmPassword = MutableStateFlow(TextFieldData())

    init {
        email.initTextField()
        username.initTextField()
        password.initTextField()
        confirmPassword.initTextField()
    }

    fun onRegisterButtonClicked() {

    }
}