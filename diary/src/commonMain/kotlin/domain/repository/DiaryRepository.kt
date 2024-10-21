package domain.repository

import domain.model.Product
import kotlinx.datetime.LocalDateTime
import utils.Resource

interface DiaryRepository {
    suspend fun getUserProducts(latestDateTime: LocalDateTime?): Resource<List<Product>>

    suspend fun getOfflineProducts(
        userId: String? = null,
        searchText: String? = null,
        limit: Int,
        skip: Int = 0,
    ): Resource<List<Product>>
}