import data.BaseRepository
import data.api.DiaryDao
import domain.model.NutritionValues
import domain.model.Product
import domain.model.ProductDiaryEntry
import domain.model.Recipe
import domain.model.RecipeDiaryEntry
import domain.repository.DiaryRepository
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

    override suspend fun getOfflineProducts(
        userId: String?,
        searchText: String?,
        limit: Long,
        skip: Long,
    ): Resource<List<Product>> {
        return handleRequest {
            diaryDao.getProducts(
                userId = userId,
                searchText = searchText,
                limit = limit,
                offset = skip
            )
        }
    }

    override suspend fun insertOfflineProducts(products: List<Product>): Resource<Unit> {
        return handleRequest {
            products.forEach { insertOfflineProduct(it) }
        }
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

    override suspend fun getOfflineProductDiaryEntries(limit: Long): Resource<List<ProductDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineProductDiaryEntries(date: LocalDate): Resource<List<ProductDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOfflineProductDiaryEntries(productDiaryEntries: List<ProductDiaryEntry>): Resource<Unit> {
        TODO("Not yet implemented")
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

    override suspend fun getOfflineRecipes(
        userId: String?,
        searchText: String?,
        limit: Long,
        skip: Long
    ): Resource<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipe(recipeId: String): Resource<Recipe?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOfflineRecipes(recipes: List<Recipe>): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOfflineRecipe(recipe: Recipe): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipeDiaryEntries(
        searchText: String?,
        limit: Long,
        skip: Long
    ): Resource<List<RecipeDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipeDiaryEntries(limit: Long): Resource<List<RecipeDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfflineRecipeDiaryEntries(date: LocalDate): Resource<List<RecipeDiaryEntry>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertOfflineRecipeDiaryEntries(recipeDiaryEntries: List<RecipeDiaryEntry>): Resource<Unit> {
        TODO("Not yet implemented")
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