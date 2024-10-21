import data.BaseRepository
import data.api.DiaryDao
import domain.model.Product
import domain.repository.DiaryRepository
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
        limit: Int,
        skip: Int,
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
}