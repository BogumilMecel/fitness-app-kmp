package utils.providers

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import domain.model.User
import domain.services.SettingsService
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SettingsServiceImp : SettingsService {

    private val settings = Settings()
    private val observableSettings = settings as ObservableSettings

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
        private const val USER_KEY = "user_key"
    }

    override fun saveAccessToken(token: String) = settings.putString(
        key = ACCESS_TOKEN_KEY,
        value = token
    )

    override fun getAccessToken() = settings.getStringOrNull(key = ACCESS_TOKEN_KEY)

    @OptIn(ExperimentalSettingsApi::class)
    override fun getUser() = observableSettings.getStringOrNullFlow(key = USER_KEY).map {
        stringToObject<User>(it)
    }

    override fun setUser(user: User) {
        objectToString(user)?.let {
            settings.putString(
                key = USER_KEY,
                value = it
            )
        }
    }

    private inline fun <reified T> stringToObject(string: String?) = try {
        string?.let {
            Json.decodeFromString<T>(string)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    private inline fun <reified T> objectToString(item: T) = try {
        Json.encodeToString(value = item)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}