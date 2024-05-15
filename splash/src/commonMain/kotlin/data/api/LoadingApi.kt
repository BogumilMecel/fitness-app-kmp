package data.api

import domain.model.DiaryCacheRequest
import domain.model.DiaryCacheResponse
import domain.model.user.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType


class LoadingApi(private val httpClient: HttpClient) {
    suspend fun authenticate() = httpClient.post {
        url(urlString = "/authentication/authenticate")
    }.body<User>()

    suspend fun getCacheDiary(diaryCacheRequest: DiaryCacheRequest) = httpClient.post {
        url(urlString = "/userData/diary-cache")
        setBody(diaryCacheRequest)
        contentType(ContentType.Application.Json)
    }.body<DiaryCacheResponse>()
}