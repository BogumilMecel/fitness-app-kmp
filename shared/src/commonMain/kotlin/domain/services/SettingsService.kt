package domain.services

import domain.model.User
import kotlinx.coroutines.flow.Flow

interface SettingsService {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun getUser(): Flow<User?>
    fun getNotNullUser(): User
    fun setUser(user: User)
    fun setAvailableDiaryDatesCount(availableDiaryDatesCount: Int)
    fun getAvailableDiaryDatesCount(): Int
}