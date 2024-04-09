package domain.model

import dev.icerock.moko.resources.StringResource

interface ResourceProvider {
    fun getString(
        resource: StringResource,
        args: List<Any> = emptyList()
    ): String
}