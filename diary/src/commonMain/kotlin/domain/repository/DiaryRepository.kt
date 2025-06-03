package domain.repository

import domain.model.DeleteDiaryEntryRequest
import domain.model.DiaryEntriesResponse
import domain.model.NutritionValues
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import utils.Resource

interface DiaryRepository {
    suspend fun getDiaryEntries(date: LocalDate): Resource<DiaryEntriesResponse>

    suspend fun getProductDiaryEntries(latestDateTime: LocalDateTime?): Resource<List<ProductDiaryEntry>>

    suspend fun getRecipeDiaryEntries(latestDateTime: LocalDateTime?): Resource<List<RecipeDiaryEntry>>

    suspend fun searchForProducts(searchText: String, page: Int): Resource<List<Product>>

    suspend fun searchForProductWithBarcode(barcode: String): Resource<Product?>

    suspend fun searchForRecipes(searchText: String): Resource<List<Recipe>>

    suspend fun insertProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<ProductDiaryEntry>

    suspend fun insertRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<RecipeDiaryEntry>

    suspend fun getRecipe(recipeId: String): Resource<Recipe?>

    suspend fun deleteDiaryEntry(deleteDiaryEntryRequest: DeleteDiaryEntryRequest): Resource<Unit>

    suspend fun editProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<ProductDiaryEntry>

    suspend fun editRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<RecipeDiaryEntry>

    suspend fun saveNewProduct(product: Product): Resource<Product>

    suspend fun getProduct(productId: String): Resource<Product?>

    suspend fun addNewRecipe(recipe: Recipe): Resource<Recipe>

    suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>>

    suspend fun getUserRecipes(latestDateTime: LocalDateTime?): Resource<List<Recipe>>

    suspend fun getOfflineProducts(
        userId: String? = null,
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): List<Product>

    suspend fun getOfflineProduct(productId: String): Resource<Product?>

    suspend fun insertOfflineProducts(products: List<Product>)

    suspend fun insertOfflineProduct(product: Product): Resource<Unit>

    suspend fun getOfflineProductDiaryEntries(
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Resource<List<ProductDiaryEntry>>

    suspend fun getOfflineProductDiaryEntries(limit: Long): Flow<List<ProductDiaryEntry>>

    fun getOfflineProductDiaryEntries(date: LocalDate): Flow<List<ProductDiaryEntry>>

    suspend fun insertOfflineProductDiaryEntries(productDiaryEntries: List<ProductDiaryEntry>)

    suspend fun insertOfflineProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<Unit>

    suspend fun deleteOfflineProductDiaryEntries(date: LocalDate): Resource<Unit>

    suspend fun deleteOfflineProductDiaryEntry(productDiaryEntryId: String): Resource<Unit>

    fun getOfflineRecipes(
        userId: String? = null,
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Flow<List<Recipe>>

    suspend fun getOfflineRecipe(recipeId: String): Resource<Recipe?>

    suspend fun insertOfflineRecipes(recipes: List<Recipe>)

    suspend fun insertOfflineRecipe(recipe: Recipe): Resource<Unit>

    suspend fun getOfflineRecipeDiaryEntries(
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Flow<List<RecipeDiaryEntry>>

    suspend fun getOfflineRecipeDiaryEntries(limit: Long): Flow<List<RecipeDiaryEntry>>

    fun getOfflineRecipeDiaryEntries(date: LocalDate): Flow<List<RecipeDiaryEntry>>

    suspend fun insertOfflineRecipeDiaryEntries(recipeDiaryEntries: List<RecipeDiaryEntry>)

    suspend fun insertOfflineRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<Unit>

    suspend fun deleteOfflineRecipeDiaryEntries(date: LocalDate): Resource<Unit>

    suspend fun deleteOfflineRecipeDiaryEntry(recipeDiaryEntryId: String): Resource<Unit>

    suspend fun getOfflineDiaryEntriesNutritionValues(date: LocalDate): Resource<List<NutritionValues>>
}