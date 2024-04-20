package utils.providers

import com.russhwolf.settings.Settings
import domain.services.SettingsService

class SettingsServiceImp(private val settings: Settings): SettingsService {

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
    }

    override fun saveAccessToken(token: String) {
        settings.putString(
            key = ACCESS_TOKEN_KEY,
            value = token
        )
    }

    override fun getAccessToken() = settings.getStringOrNull(key = ACCESS_TOKEN_KEY)
}