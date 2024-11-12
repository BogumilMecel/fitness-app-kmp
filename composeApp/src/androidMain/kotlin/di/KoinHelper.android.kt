package di

import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual fun startDi(nativeModule: Module) {
    if (GlobalContext.getOrNull() == null) {
        startKoin {
            modules(nativeModule + sharedModules)
        }
    }
}