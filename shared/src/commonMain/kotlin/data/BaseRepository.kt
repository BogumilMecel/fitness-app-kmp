package data

import utils.Resource

open class BaseRepository {
    inline fun <R> handleRequest(block: () -> R): Resource<R> {
        return try {
            Resource.Success(block())
        } catch (exception: Exception) {
            exception.printStackTrace()
            Resource.ComplexError(exception = exception)
        }
    }
}