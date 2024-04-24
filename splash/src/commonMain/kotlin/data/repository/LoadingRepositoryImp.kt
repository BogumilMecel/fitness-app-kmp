package data.repository

import data.api.LoadingApi
import domain.repository.LoadingRepository
import data.BaseRepository
import domain.model.User
import utils.Resource

class LoadingRepositoryImp(private val loadingApi: LoadingApi) : LoadingRepository, BaseRepository() {

    override suspend fun authenticateUser(): Resource<User> {
        return handleRequest {
            loadingApi.authenticate()
        }
    }
}