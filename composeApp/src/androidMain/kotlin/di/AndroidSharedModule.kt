package di

import android.content.Context
import utils.providers.ResourceProviderImp

fun getAndroidSharedModule(context: Context) = createSharedNativeModule(
    resourceProvider = { ResourceProviderImp(context = context) }
)