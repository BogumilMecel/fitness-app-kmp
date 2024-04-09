package di

import utils.providers.ResourceProviderImp

val iosSharedModule = createSharedNativeModule(
    resourceProvider = { ResourceProviderImp() }
)