package com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.repository

import com.gmail.bogumilmecel2.fitnessappv2.common.util.Resource
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.LoginRequest
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.RegisterRequest
import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.TokenResponse

interface AuthRepository {

    suspend fun logInUser(
        loginRequest: LoginRequest
    ): Resource<TokenResponse>

    suspend fun registerUser(
        registerRequest: RegisterRequest
    ): Resource<Unit>

    suspend fun sendPasswordResetEmail(
        email: String
    ): Resource<Boolean>
}