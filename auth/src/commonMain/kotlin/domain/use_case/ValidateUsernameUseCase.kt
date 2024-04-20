package domain.use_case

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.services.ResourcesService
import utils.Resource

class ValidateUsernameUseCase(private val resourcesService: ResourcesService) {
    operator fun invoke(username: String): Resource<Unit> {
        if (username.isEmpty()) return Resource.Error(
            uiText = resourcesService.getString(SharedRes.strings.empty_fields_error)
        )

        return Resource.Success(Unit)
    }
}