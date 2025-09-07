package data.repository

import data.BaseRepository
import data.api.IntroductionApi
import domain.model.IntroductionRequest
import domain.model.IntroductionResponse
import domain.repository.IntroductionRepository

class IntroductionRepositoryImp(
    private val introductionApi: IntroductionApi
) : IntroductionRepository, BaseRepository() {

    override suspend fun saveUserInformation(introductionRequest: IntroductionRequest): IntroductionResponse {
        return introductionApi.saveUserInformation(introductionRequest = introductionRequest)
    }
} 