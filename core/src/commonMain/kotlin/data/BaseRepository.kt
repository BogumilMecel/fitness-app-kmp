package data

import org.lighthousegames.logging.logging
import utils.Resource

open class BaseRepository {
    inline fun <R> handleRequest(block: () -> R): Resource<R> {
        return try {
            Resource.Success(block())
        } catch (exception: Exception) {
            logging(tag = "BaseRepository").e { exception.message }
            Resource.Error(exception)
        }
    }
}