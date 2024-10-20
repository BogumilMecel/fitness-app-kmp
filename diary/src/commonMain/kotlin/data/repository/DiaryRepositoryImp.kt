import data.BaseRepository
import domain.model.Product
import domain.repository.DiaryRepository
import kotlinx.datetime.LocalDateTime
import utils.Resource

class DiaryRepositoryImp(private val diaryApi: DiaryApi) : DiaryRepository, BaseRepository() {
    override suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>> {
        return handleRequest {
            diaryApi.getUserProducts(latestDateTime = latestDateTime)
        }
    }
}