package utils

import io.ktor.client.plugins.ClientRequestException

fun Exception.getHttpCode() = if (this is ClientRequestException) this.response.status.value else null