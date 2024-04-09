package utils.providers

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import domain.model.ResourceProvider

actual class ResourceProviderImp(private val context: Context) : ResourceProvider {
    override fun getString(resource: StringResource, args: List<Any>): String {
        return if (args.isEmpty()) {
            StringDesc.Resource(resource).toString(context = context)
        } else {
            resource.format(args).toString(context = context)
        }
    }
}