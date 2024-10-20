import constans.Constants
import domain.model.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import kotlinx.datetime.LocalDateTime

class DiaryApi(private val httpClient: HttpClient) {
    suspend fun getUserProducts(latestDateTime: LocalDateTime?) = httpClient.get {
        parameters {
            latestDateTime?.toString()?.let {
                append(
                    name = Constants.Query.LATEST_DATE_TIME,
                    value = it
                )
            }
        }
    }.body<List<Product>>()
}