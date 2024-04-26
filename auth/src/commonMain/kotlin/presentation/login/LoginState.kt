package presentation.login

import components.TextFieldData

data class LoginState(
    val email: TextFieldData = TextFieldData(),
    val password: TextFieldData = TextFieldData(),
)