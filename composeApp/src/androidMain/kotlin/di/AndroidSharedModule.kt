package di

import android.content.Context
import utils.providers.ResourcesServiceImp

fun getAndroidSharedModule(context: Context) = createSharedNativeModule(
    resourcesService = { ResourcesServiceImp(context = context) },
)