package di

import android.content.Context
import com.russhwolf.settings.SharedPreferencesSettings
import utils.providers.ResourcesServiceImp
import utils.providers.SettingsServiceImp

fun getAndroidSharedModule(context: Context) = createSharedNativeModule(
    resourcesService = { ResourcesServiceImp(context = context) },
    settingsService = {
        SettingsServiceImp(
            settings = SharedPreferencesSettings(
                delegate = context.getSharedPreferences(
                    "Fitness app",
                    Context.MODE_PRIVATE
                )
            )
        )
    }
)