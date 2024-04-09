package di

import domain.model.ResourceProvider
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

typealias NativeInjectionFactory<T> = Scope.() -> T

fun createSharedNativeModule(
    resourceProvider: NativeInjectionFactory<ResourceProvider>
): Module {
    return module {
        single { resourceProvider() }
    }
}