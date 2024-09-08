package domain.use_case

import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.empty_fields_error
import domain.services.ResourcesService
import utils.Resource

class ValidateUsernameUseCase(private val resourcesService: ResourcesService) {
    suspend operator fun invoke(username: String): Resource<Unit> {
        if (username.isEmpty()) return Resource.Error(
            uiText = resourcesService.getString(Res.string.empty_fields_error)
        )

        return Resource.Success(Unit)
    }
}