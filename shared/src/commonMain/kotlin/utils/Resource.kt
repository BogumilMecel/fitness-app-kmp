package utils

sealed class Resource<T>(open val data: T? = null) {
    data class Success<T>(override val data: T) : Resource<T>()
    data class Error<T>(val exception: Exception) : Resource<T>() {
        constructor(uiText: String) : this(exception = Exception(uiText))

        companion object {
            fun <T> createSimple(message: String) = Error<T>(
                exception = Exception(message)
            )
        }

        fun getHttpCode(): Int? = exception.getHttpCode()
    }
}

fun <T> Resource.Error<*>.copyType() = Resource.Error<T>(exception = exception)