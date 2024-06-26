package utils.providers

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.format
import domain.services.ResourcesService

actual class ResourcesServiceImp : ResourcesService {
    override fun getString(resource: StringResource, args: List<Any>): String {
        return if (args.isEmpty()) {
            StringDesc.Resource(resource).localized()
        } else {
            resource.format(*args.toTypedArray()).localized()
        }
    }
}