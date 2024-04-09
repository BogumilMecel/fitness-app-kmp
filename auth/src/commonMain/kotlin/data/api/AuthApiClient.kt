package data.api

import domain.model.AuthRequest
import domain.model.AuthResponse
import io.ktor.client.call.body

class AuthApiClient(private val baseApiClient: BaseApiClient) {

    suspend fun login(authRequest: AuthRequest) = baseApiClient.post(
        route = "/authentication/signup/",
        body = authRequest,
    ).body<AuthResponse>()

    suspend fun register(authRequest: AuthRequest) = baseApiClient.post(
        route = "/authentication/signin/",
        body = authRequest,
    ).body<Unit>()
}