package utils

sealed class Resource<T>(open val data: T? = null) {
    data class Success<T>(override val data: T) : Resource<T>()
    open class Error<T>(val uiText: String = "unknown error") : Resource<T>() {
        fun getHttpCode(): Int? = (this as? ComplexError)?.exception?.getHttpCode()
    }
    data class ComplexError<T>(val exception: Exception) :
        Error<T>(uiText = exception.message ?: "unknown error")
}

fun <T> Resource.ComplexError<*>.copyType() = Resource.ComplexError<T>(
    exception = exception,
)