package utils.flow

import components.TextFieldData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

fun MutableStateFlow<TextFieldData>.getText() = value.text

fun MutableStateFlow<TextFieldData>.setError(error: String?) = update {
    it.copy(error = error)
}

fun MutableStateFlow<TextFieldData>.isNotError() = value.error == null

fun MutableStateFlow<TextFieldData>.initTextField(
    initialValue: String = "",
    onValueChange: ((String) -> Unit)? = null
) {
    value = TextFieldData(
        text = initialValue,
        onValueChange = onValueChange ?: { newValue -> update { it.copy(text = newValue) } },
        onErrorCleared = { update { it.copy(error = null) } }
    )
}