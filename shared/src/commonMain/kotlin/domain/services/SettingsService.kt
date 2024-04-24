package domain.services

import domain.model.User

interface SettingsService {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
    fun getUser(): User?
    fun setUser(user: User)
}