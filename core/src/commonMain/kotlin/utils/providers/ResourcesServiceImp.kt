package utils.providers

import domain.services.ResourcesService
import org.jetbrains.compose.resources.StringResource

class ResourcesServiceImp: ResourcesService {
    override suspend fun getString(resource: StringResource, args: List<Any>): String {
        return try {
            org.jetbrains.compose.resources.getString(resource = resource, args)
        } catch (e: Exception) {
            ""
        }
    }
}