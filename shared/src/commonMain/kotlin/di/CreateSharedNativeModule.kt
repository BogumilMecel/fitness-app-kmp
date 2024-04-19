package di

import domain.model.ResourcesService
import domain.model.SettingsService
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

typealias NativeInjectionFactory<T> = Scope.() -> T

fun createSharedNativeModule(
    resourcesService: NativeInjectionFactory<ResourcesService>,
    settingsService: NativeInjectionFactory<SettingsService>
): Module {
    return module {
        single { resourcesService() }
        single { settingsService() }
    }
}