package data.api

import domain.model.IntroductionRequest
import domain.model.IntroductionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

class IntroductionApi(private val httpClient: HttpClient) {

    suspend fun saveUserInformation(introductionRequest: IntroductionRequest) = httpClient.post {
        url(urlString = "/userData/userInformation/")
        setBody(introductionRequest)
    }.body<IntroductionResponse>()
} 