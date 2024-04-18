package domain.model

interface CachedValuesProvider {
    fun saveAccessToken(token: String)
    fun getAccessToken(): String?
}