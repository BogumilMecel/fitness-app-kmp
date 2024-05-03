package domain.repository

import domain.model.AuthRequest
import domain.model.AuthResponse
import utils.Resource

interface AuthRepository {

    suspend fun logInUser(
        authRequest: AuthRequest
    ): Resource<AuthResponse>

    suspend fun registerUser(
        authRequest: AuthRequest
    ): Resource<AuthResponse>

    suspend fun sendPasswordResetEmail(
        authRequest: AuthRequest
    ): Resource<Unit>
}