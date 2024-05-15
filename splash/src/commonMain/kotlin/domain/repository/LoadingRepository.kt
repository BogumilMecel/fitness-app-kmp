package domain.repository

import domain.model.DiaryCacheRequest
import domain.model.DiaryCacheResponse
import domain.model.user.User
import utils.Resource

interface LoadingRepository {
    suspend fun authenticateUser(): Resource<User>
    suspend fun getDiaryCache(diaryCacheRequest: DiaryCacheRequest): Resource<DiaryCacheResponse>
}