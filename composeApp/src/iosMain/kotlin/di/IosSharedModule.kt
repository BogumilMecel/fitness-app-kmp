package di

import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults
import utils.providers.ResourcesServiceImp
import utils.providers.SettingsServiceImp

val iosSharedModule = createSharedNativeModule(
    resourcesService = { ResourcesServiceImp() },
    settingsService = {
        SettingsServiceImp(
            settings = NSUserDefaultsSettings(
                delegate = NSUserDefaults.standardUserDefaults
            )
        )
    }
)