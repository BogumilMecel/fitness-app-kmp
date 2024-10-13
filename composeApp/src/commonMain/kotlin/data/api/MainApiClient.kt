package data.api

import domain.model.AvailableDiaryDatesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class MainApiClient(private val httpClient: HttpClient) {

    suspend fun requestAvailableDiaryDates() = httpClient.get {
        url(urlString = "/available_diary_dates/")
    }.body<AvailableDiaryDatesResponse>()
}