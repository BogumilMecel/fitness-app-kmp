package domain.repository

import domain.model.IntroductionRequest
import domain.model.IntroductionResponse

interface IntroductionRepository {
    suspend fun saveUserInformation(introductionRequest: IntroductionRequest): IntroductionResponse
} 