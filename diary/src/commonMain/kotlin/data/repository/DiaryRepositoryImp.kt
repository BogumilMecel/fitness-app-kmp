package data.repository

import DiaryApi
import data.BaseRepository
import data.api.DiaryDao
import domain.model.DeleteDiaryEntryRequest
import domain.model.DiaryEntriesResponse
import domain.model.NutritionValues
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import utils.Resource

class DiaryRepositoryImp(
    private val diaryApi: DiaryApi,
    private val diaryDao: DiaryDao,
) : DiaryRepository, BaseRepository() {
    override suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>> {
        return handleRequest {
            diaryApi.getUserProducts(latestDateTime = latestDateTime)
        }
    }

    override suspend fun getDiaryEntries(date: LocalDate): Resource<DiaryEntriesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDiaryEntries(latestDateTime: LocalDateTime?): Resource<List<ProductDiaryEntry>> {
        return handleRequest {
            diaryApi.getUserProductDiaryEntries(latestDateTime)
        }
    }

    override suspend fun getRecipeDiaryEntries(latestDateTime: LocalDateTime?): Resource<List<RecipeDiaryEntry>> {
        return handleRequest {
            diaryApi.getUserRecipeDiaryEntries(latestDateTime)
        }
    }

    override suspend fun searchForProducts(searchText: String, page: Int): Resource<List<Product>> {
        return handleRequest {
            diaryApi.searchForProducts(searchText = searchText, page = page)
        }
    }

    override suspend fun searchForProductWithBarcode(barcode: String): Resource<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun searchForRecipes(searchText: String): Resource<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<ProductDiaryEntry> {
        TODO("Not yet implemented")
    }

    override suspend fun insertRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<RecipeDiaryEntry> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipe(recipeId: String): Resource<Recipe?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDiaryEntry(deleteDiaryEntryRequest: DeleteDiaryEntryRequest): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun editProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<ProductDiaryEntry> {
        TODO("Not yet implemented")
    }

    override suspend fun editRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<RecipeDiaryEntry> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewProduct(product: Product): Resource<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(productId: String): Resource<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewRecipe(recipe: Recipe): Resource<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserRecipes(latestDateTime: LocalDateTime?): Resource<List<Recipe>> {
        return handleRequest {
            diaryApi.getUserRecipes(latestDateTime)
        }
    }

    override suspend fun getOfflineProducts(
        userId: String?,
        searchText: String?,
        limit: Long,
        skip: Long,
    ): List<Product> {
        return diaryDao.getProducts(
            userId = userId,
            searchText = searchText,
            limit = limit,
            offset = skip
        )
    }

    override suspend fun insertOfflineProducts(products: List<Product>) {
        diaryDao.insertProducts(products)
    }

    override suspend fun insertOfflineProduct(product: Product): Resource<Unit> {
        return handleRequest {
            diaryDao.insertProduct(product = product)
        }
    }

    override suspend fun getOfflineProduct(productId: String): Resource<Product?> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineProductDiaryEntries(
        searchText: String?,
        limit: Long,
        skip: Long
    ): Resource<List<ProductDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineProductDiaryEntries(limit: Long): Flow<List<ProductDiaryEntry>> {
        return diaryDao.getAllProductDiaryEntries(limit)
    }

    override fun getOfflineProductDiaryEntries(date: LocalDate): Flow<List<ProductDiaryEntry>> {
        return diaryDao.getProductDiaryEntriesByDate(date)
    }

    override suspend fun insertOfflineProductDiaryEntries(productDiaryEntries: List<ProductDiaryEntry>) {
        diaryDao.insertProductDiaryEntries(productDiaryEntries)
    }

    override suspend fun insertOfflineProductDiaryEntry(productDiaryEntry: ProductDiaryEntry): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOfflineProductDiaryEntries(date: LocalDate): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOfflineProductDiaryEntry(productDiaryEntryId: String): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override fun getOfflineRecipes(
        userId: String?,
        searchText: String?,
        limit: Long,
        skip: Long
    ): Flow<List<Recipe>> {
        return diaryDao.getUserRecipes(userId = userId, searchText = searchText, limit = limit, offset = skip)
    }

    override suspend fun getOfflineRecipe(recipeId: String): Resource<Recipe?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOfflineRecipes(recipes: List<Recipe>) {
        diaryDao.insertRecipes(recipes)
    }

    override suspend fun insertOfflineRecipe(recipe: Recipe): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipeDiaryEntries(
        searchText: String?,
        limit: Long,
        skip: Long
    ): Flow<List<RecipeDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipeDiaryEntries(limit: Long): Flow<List<RecipeDiaryEntry>> {
        return diaryDao.getAllRecipeDiaryEntries(limit)
    }

    override fun getOfflineRecipeDiaryEntries(date: LocalDate): Flow<List<RecipeDiaryEntry>> {
        return diaryDao.getRecipeDiaryEntriesByDate(date)
    }

    override suspend fun insertOfflineRecipeDiaryEntries(recipeDiaryEntries: List<RecipeDiaryEntry>) {
        diaryDao.insertRecipeDiaryEntries(recipeDiaryEntries)
    }

    override suspend fun insertOfflineRecipeDiaryEntry(recipeDiaryEntry: RecipeDiaryEntry): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOfflineRecipeDiaryEntries(date: LocalDate): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOfflineRecipeDiaryEntry(recipeDiaryEntryId: String): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineDiaryEntriesNutritionValues(date: LocalDate): Resource<List<NutritionValues>> {
        TODO("Not yet implemented")
    }
}