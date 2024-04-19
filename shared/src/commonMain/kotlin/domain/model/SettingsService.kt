package domain.model

interface SettingsService {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
}