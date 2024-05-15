package data.repository

import data.BaseRepository
import data.api.LoadingApi
import domain.model.DiaryCacheRequest
import domain.model.DiaryCacheResponse
import domain.model.user.User
import domain.repository.LoadingRepository
import utils.Resource

class LoadingRepositoryImp(private val loadingApi: LoadingApi) : LoadingRepository, BaseRepository() {

    override suspend fun authenticateUser(): Resource<User> {
        return handleRequest {
            loadingApi.authenticate()
        }
    }

    override suspend fun getDiaryCache(diaryCacheRequest: DiaryCacheRequest): Resource<DiaryCacheResponse> {
        return handleRequest {
            loadingApi.getCacheDiary(diaryCacheRequest = diaryCacheRequest)
        }
    }
}