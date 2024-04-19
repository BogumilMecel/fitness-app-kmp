package domain.use_case

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.model.ResourcesService
import utils.Resource

class ValidateEmailUseCase(private val resourcesService: ResourcesService) {
    operator fun invoke(email: String): Resource<Unit> {
        if (email.isEmpty()) return Resource.Error(
            uiText = resourcesService.getString(SharedRes.strings.empty_fields_error)
        )

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        if (!email.matches(emailRegex)) return Resource.Error(
            uiText = resourcesService.getString(SharedRes.strings.invalid_email_error)
        )

        return Resource.Success(Unit)
    }
}