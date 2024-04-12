package utils

import io.ktor.client.plugins.ClientRequestException

sealed class Resource<T>(open val data: T? = null) {
    data class Success<T>(override val data: T) : Resource<T>()
    data class Error<T>(
        val uiText: String = "unknown error",
        val exception: Exception? = null
    ) : Resource<T>() {

        companion object {
            fun <T> createFromException(exception: Exception) = Error<T>(
                exception = exception,
                uiText = exception.message ?: exception.getHttpCode()?.let {
                    "Error $it"
                } ?: "unknown error"
            )
        }

        fun getHttpCode(): Int? = exception?.getHttpCode()
    }
}

fun <T> Resource.Error<*>.copyType() = Resource.Error<T>(
    uiText = uiText,
    exception = exception,
)