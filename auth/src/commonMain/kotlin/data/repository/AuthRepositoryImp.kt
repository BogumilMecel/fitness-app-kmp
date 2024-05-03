package data.repository

import domain.repository.AuthRepository
import data.BaseRepository
import data.api.AuthApiClient
import domain.model.AuthRequest
import domain.model.AuthResponse
import utils.Resource

class AuthRepositoryImp(
    private val authApiClient: AuthApiClient
) : AuthRepository, BaseRepository() {
    override suspend fun logInUser(authRequest: AuthRequest): Resource<AuthResponse> {
        return handleRequest { authApiClient.login(authRequest) }
    }

    override suspend fun registerUser(authRequest: AuthRequest): Resource<AuthResponse> {
        return handleRequest { authApiClient.register(authRequest) }
    }

    override suspend fun sendPasswordResetEmail(authRequest: AuthRequest): Resource<Unit> {
        TODO("Not yet implemented")
    }
}