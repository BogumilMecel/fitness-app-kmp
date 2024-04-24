package domain.repository

import domain.model.User
import utils.Resource

interface LoadingRepository {
    suspend fun authenticateUser(): Resource<User>
}