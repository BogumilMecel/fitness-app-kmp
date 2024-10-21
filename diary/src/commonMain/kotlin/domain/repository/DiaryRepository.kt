package domain.repository

import domain.model.NutritionValues
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import utils.Resource

interface DiaryRepository {
    suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>>

    suspend fun getOfflineProducts(
        userId: String? = null,
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Resource<List<Product>>

    suspend fun getOfflineProduct(productId: String): Resource<Product?>

    suspend fun insertOfflineProducts(products: List<Product>): Resource<Unit>

    suspend fun insertOfflineProduct(product: Product): Resource<Unit>

    suspend fun getOfflineProductDiaryEntries(
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Resource<List<ProductDiaryEntry>>

    suspend fun getOfflineProductDiaryEntries(limit: Long): Resource<List<ProductDiaryEntry>>

    suspend fun getOfflineProductDiaryEntries(date: LocalDate): Resource<List<ProductDiaryEntry>>

    suspend fun insertOfflineProductDiaryEntries(productDiaryEntries: List<ProductDiaryEntry>): Resource<Unit>

    suspend fun insertOfflineProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<Unit>

    suspend fun deleteOfflineProductDiaryEntries(date: LocalDate): Resource<Unit>

    suspend fun deleteOfflineProductDiaryEntry(productDiaryEntryId: String): Resource<Unit>

    suspend fun getOfflineRecipes(
        userId: String? = null,
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Resource<List<Recipe>>

    suspend fun getOfflineRecipe(recipeId: String): Resource<Recipe?>

    suspend fun insertOfflineRecipes(recipes: List<Recipe>): Resource<Unit>

    suspend fun insertOfflineRecipe(recipe: Recipe): Resource<Unit>

    suspend fun getOfflineRecipeDiaryEntries(
        searchText: String? = null,
        limit: Long,
        skip: Long = 0,
    ): Resource<List<RecipeDiaryEntry>>

    suspend fun getOfflineRecipeDiaryEntries(limit: Long): Resource<List<RecipeDiaryEntry>>

    suspend fun getOfflineRecipeDiaryEntries(date: LocalDate): Resource<List<RecipeDiaryEntry>>

    suspend fun insertOfflineRecipeDiaryEntries(recipeDiaryEntries: List<RecipeDiaryEntry>): Resource<Unit>

    suspend fun insertOfflineRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<Unit>

    suspend fun deleteOfflineRecipeDiaryEntries(date: LocalDate): Resource<Unit>

    suspend fun deleteOfflineRecipeDiaryEntry(recipeDiaryEntryId: String): Resource<Unit>

    suspend fun getOfflineDiaryEntriesNutritionValues(date: LocalDate): Resource<List<NutritionValues>>
}