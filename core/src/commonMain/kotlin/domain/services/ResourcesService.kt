package domain.services

import org.jetbrains.compose.resources.StringResource

interface ResourcesService {
    suspend fun getString(
        resource: StringResource,
        args: List<Any> = emptyList()
    ): String
}