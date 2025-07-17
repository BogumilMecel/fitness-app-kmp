package utils

import components.TextFieldData
import kotlinx.coroutines.flow.MutableStateFlow
import utils.exception.getHttpCode
import utils.flow.setError

sealed class Resource<T>(open val data: T? = null) {
    data class Success<T>(override val data: T) : Resource<T>()
    data class Error<T>(val exception: Exception) : Resource<T>() {
        constructor(uiText: String = "unknown error") : this(exception = Exception(uiText))
        fun getHttpCode(): Int? = exception.getHttpCode()
    }
}

fun <T> Resource.Error<*>.copyType() = Resource.Error<T>(exception = exception)

fun <T> Resource<T>.handle(validationField: MutableStateFlow<TextFieldData>) {
    handle(
        onError = { validationField.setError(it.message) },
        onSuccess = { validationField.setError(null) }
    )
}

inline fun <T> Resource<T>.handle(
    finally: () -> Unit = {},
    onError: (Exception) -> Unit = {},
    onSuccess: (T) -> Unit = {}
) {
    when (this) {
        is Resource.Error -> {
            onError(exception)
        }

        is Resource.Success -> {
            onSuccess(data)
        }
    }
    finally()
}