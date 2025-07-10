package utils.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    @SerialName("errors")
    val errors: List<Error>? = null,

    @SerialName("message")
    val message: String? = null
)