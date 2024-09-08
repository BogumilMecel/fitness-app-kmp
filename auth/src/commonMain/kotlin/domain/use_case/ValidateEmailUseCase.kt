package domain.use_case

import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.empty_fields_error
import com.gmail.bogumilmecel2.ui.composeResources.invalid_email_error
import domain.services.ResourcesService
import utils.Resource

class ValidateEmailUseCase(private val resourcesService: ResourcesService) {
    suspend operator fun invoke(email: String): Resource<Unit> {
        if (email.isEmpty()) return Resource.Error(
            uiText = resourcesService.getString(Res.string.empty_fields_error)
        )

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        if (!email.matches(emailRegex)) return Resource.Error(
            uiText = resourcesService.getString(Res.string.invalid_email_error)
        )

        return Resource.Success(Unit)
    }
}