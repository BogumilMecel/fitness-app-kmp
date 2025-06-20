import constans.Constants
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
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
        url(urlString = "/userData/products")
    }.body<List<Product>>()

    suspend fun getUserProductDiaryEntries(latestDateTime: LocalDateTime?) = httpClient.get {
        parameters {
            latestDateTime?.toString()?.let {
                append(
                    name = Constants.Query.LATEST_DATE_TIME,
                    value = it
                )
            }
        }
        url(urlString = "/userData/product_diary")
    }.body<List<ProductDiaryEntry>>()

    suspend fun getUserRecipes(latestDateTime: LocalDateTime?) = httpClient.get {
        parameters {
            latestDateTime?.toString()?.let {
                append(
                    name = Constants.Query.LATEST_DATE_TIME,
                    value = it
                )
            }
        }
        url(urlString = "/userData/recipes")
    }.body<List<Recipe>>()

    suspend fun getUserRecipeDiaryEntries(latestDateTime: LocalDateTime?) = httpClient.get {
        parameters {
            latestDateTime?.toString()?.let {
                append(
                    name = Constants.Query.LATEST_DATE_TIME,
                    value = it
                )
            }
        }
        url(urlString = "/userData/recipe_diary")
    }.body<List<RecipeDiaryEntry>>()

    suspend fun searchForProducts(
        searchText: String,
        page: Int
    ) = httpClient.get {
        parameters {
            append(
                name = Constants.Query.PAGE,
                value = page.toString()
            )
        }
        url(urlString = "/products/$searchText")
    }.body<List<Product>>()
}