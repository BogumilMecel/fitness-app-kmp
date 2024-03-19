package com.gmail.bogumilmecel2.fitnessappv2.feature_auth.data.repository

import com.gmail.bogumilmecel2.fitnessappv2.common.util.BaseRepository
import com.gmail.bogumilmecel2.fitnessappv2.common.util.Resource
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.data.api.AuthApi
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.LoginRequest
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.RegisterRequest
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.TokenResponse
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.repository.AuthRepository

class AuthRepositoryImp(
    private val authApi: AuthApi
) : AuthRepository, BaseRepository() {

    override suspend fun logInUser(
        loginRequest: LoginRequest
    ): Resource<TokenResponse> {
        return handleRequest {
            authApi.signIn(request = loginRequest)
        }
    }

    override suspend fun registerUser(
        registerRequest: RegisterRequest
    ): Resource<Unit> {
        return handleRequest {
            authApi.registerUser(request = registerRequest)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Resource<Boolean> {
        return Resource.Error()
    }
}