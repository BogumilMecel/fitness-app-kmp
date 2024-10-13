package data.repository

import data.BaseRepository
import data.api.MainApiClient
import domain.model.AvailableDiaryDatesResponse
import domain.repository.MainRepository
import utils.Resource

class MainRepositoryImp(private val mainApiClient: MainApiClient) : MainRepository, BaseRepository() {
    override suspend fun requestAvailableDates(): Resource<AvailableDiaryDatesResponse> {
        return handleRequest {
            mainApiClient.requestAvailableDiaryDates()
        }
    }
}