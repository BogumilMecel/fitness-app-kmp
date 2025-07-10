package utils.exception

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("description")
    val description: String? = null,

    @SerialName("field")
    val field: String? = null,
)