package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.parameters
import kotlinx.datetime.LocalDateTime
import models.NewProductRequest
import models.Product
import models.ProductDiaryEntry
import models.ProductsResponse
import models.Recipe
import models.RecipeDiaryEntry
import utils.constans.Constants

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
        url(urlString = "/products")
        parameter(key = Constants.Query.PAGE, value = page)
        parameter(Constants.Query.SEARCH_TEXT, searchText)
    }.body<ProductsResponse>()

    suspend fun insertProduct(newProductRequest: NewProductRequest) = httpClient.post {
        url(urlString = "/products")
        setBody(newProductRequest)
    }.body<Product>()

    suspend fun getProduct(productId: String) = httpClient.get {
        url(urlString = "/products/$productId")
    }.body<Product?>()
}