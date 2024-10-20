package domain.repository

import domain.model.Product
import kotlinx.datetime.LocalDateTime
import utils.Resource

interface DiaryRepository {
    suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>>
}