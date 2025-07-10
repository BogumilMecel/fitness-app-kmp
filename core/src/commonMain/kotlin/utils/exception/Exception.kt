package utils.exception

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException

fun Exception.getHttpCode() = if (this is ClientRequestException) response.status.value else null

suspend inline fun Exception.getErrorMessage(callback: (ErrorMessage) -> Unit) =
    if (this is ClientRequestException) callback(response.body<ErrorMessage>())
    else null