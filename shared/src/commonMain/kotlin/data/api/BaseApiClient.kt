package data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

open class BaseApiClient(private val client: HttpClient) {

    companion object {
        private const val BASE_URL = "http://192.168.0.132:8080/"

        private fun getBaseUrlWithRoute(route: String) = "$BASE_URL$route"
    }

    suspend fun get(route: String) = client.get(getBaseUrlWithRoute(route))

    suspend fun post(
        route: String,
        body: Any
    ) = client.post(getBaseUrlWithRoute(route)) {
        setBody(body)
    }
}