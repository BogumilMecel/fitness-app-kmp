package domain.services

interface SettingsService {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
}