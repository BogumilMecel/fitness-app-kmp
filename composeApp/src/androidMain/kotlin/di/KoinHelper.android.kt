package di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual fun startDi(nativeModule: Module) {
    startKoin {
        modules(nativeModule + sharedModules)
    }
}