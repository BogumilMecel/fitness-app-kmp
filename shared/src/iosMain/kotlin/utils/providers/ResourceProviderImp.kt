package utils.providers

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import domain.model.ResourceProvider

actual class ResourceProviderImp : ResourceProvider {
    override fun getString(resource: StringResource, args: List<Any>): String {
        return if (args.isEmpty()) {
            StringDesc.Resource(resource).localized()
        } else {
            resource.format(*args.toTypedArray()).localized()
        }
    }
}