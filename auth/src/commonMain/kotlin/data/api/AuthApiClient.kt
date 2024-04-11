package data.api

import domain.model.AuthRequest
import domain.model.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class AuthApiClient(private val httpClient: HttpClient) {

    suspend fun login(authRequest: AuthRequest) = httpClient.post {
        url(urlString = "/authentication/signup/")
        setBody(authRequest)
    }.body<AuthResponse>()

    suspend fun register(authRequest: AuthRequest) = httpClient.post {
        url(urlString = "/authentication/signin/")
        setBody(authRequest)
    }.body<Unit>()
}