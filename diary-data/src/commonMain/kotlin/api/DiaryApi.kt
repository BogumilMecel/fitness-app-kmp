package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import kotlinx.datetime.LocalDateTime
import models.NewProductDiaryEntryRequest
import models.NewProductRequest
import models.Product
import models.ProductDiaryEntry
import models.ProductsResponse
import models.Recipe
import models.RecipeDiaryEntry
import utils.constans.Constants

class DiaryApi(private val httpClient: HttpClient) {
    suspend fun getUserProducts(latestDateTime: LocalDateTime?) = httpClient.get {
        url(urlString = "/userData/products")
        parameter(key = Constants.Query.LATEST_DATE_TIME, value = latestDateTime)
    }.body<List<Product>>()

    suspend fun getUserProductDiaryEntries(latestDateTime: LocalDateTime?) = httpClient.get {
        url(urlString = "/userData/product_diary")
        parameter(key = Constants.Query.LATEST_DATE_TIME, value = latestDateTime)
    }.body<List<ProductDiaryEntry>>()

    suspend fun getUserRecipes(latestDateTime: LocalDateTime?) = httpClient.get {
        url(urlString = "/userData/recipes")
        parameter(key = Constants.Query.LATEST_DATE_TIME, value = latestDateTime)
    }.body<List<Recipe>>()

    suspend fun getUserRecipeDiaryEntries(latestDateTime: LocalDateTime?) = httpClient.get {
        url(urlString = "/userData/recipe_diary")
        parameter(key = Constants.Query.LATEST_DATE_TIME, value = latestDateTime)
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

    suspend fun insertProductDiaryEntry(newProductDiaryEntryRequest: NewProductDiaryEntryRequest) = httpClient.post {
        url(urlString = "/diaryEntries/product")
        setBody(newProductDiaryEntryRequest)
    }.body<ProductDiaryEntry>()

    suspend fun getProduct(productId: String) = httpClient.get {
        url(urlString = "/products/$productId")
    }.body<Product?>()
}