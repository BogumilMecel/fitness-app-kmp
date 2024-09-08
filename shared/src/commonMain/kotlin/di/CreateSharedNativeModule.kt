package di

import domain.services.ResourcesService
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

typealias NativeInjectionFactory<T> = Scope.() -> T

fun createSharedNativeModule(
    resourcesService: NativeInjectionFactory<ResourcesService>,
): Module {
    return module {
        single { resourcesService() }
    }
}