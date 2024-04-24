package data.api

import domain.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.url


class LoadingApi(private val httpClient: HttpClient) {
    suspend fun authenticate() = httpClient.post {
        url(urlString = "/authentication/authenticate")
    }.body<User>()
}