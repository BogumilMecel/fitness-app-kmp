package domain.model

import dev.icerock.moko.resources.StringResource

interface ResourcesService {
    fun getString(
        resource: StringResource,
        args: List<Any> = emptyList()
    ): String
}