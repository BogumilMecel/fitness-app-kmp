package domain.repository

import domain.model.user.User
import utils.Resource

interface LoadingRepository {
    suspend fun authenticateUser(): Resource<User>
}