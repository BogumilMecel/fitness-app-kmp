package domain.repository

import domain.model.AvailableDiaryDatesResponse
import utils.Resource

interface MainRepository {
    suspend fun requestAvailableDates(): Resource<AvailableDiaryDatesResponse>
}