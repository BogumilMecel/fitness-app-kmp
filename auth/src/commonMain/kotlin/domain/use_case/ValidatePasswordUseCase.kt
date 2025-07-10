package domain.use_case

import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.empty_fields_error
import com.gmail.bogumilmecel2.ui.composeResources.password_length_error
import com.gmail.bogumilmecel2.ui.composeResources.password_not_match_error
import domain.constants.AuthConstants
import domain.services.ResourcesService
import utils.Resource
import utils.string.isLengthInRange

class ValidatePasswordUseCase(private val resourcesService: ResourcesService) {
    suspend operator fun invoke(
        password: String,
        confirmPassword: String? = null
    ): Resource<Unit> {
        if (password.isEmpty()) return Resource.Error(
            uiText = resourcesService.getString(Res.string.empty_fields_error)
        )

        if (!password.isLengthInRange(
                minimum = AuthConstants.PASSWORD_MIN_LENGTH,
                maximum = AuthConstants.PASSWORD_MAX_LENGTH
            )
        ) return Resource.Error(
            uiText = resourcesService.getString(
                resource = Res.string.password_length_error,
                args = listOf(
                    AuthConstants.PASSWORD_MIN_LENGTH,
                    AuthConstants.PASSWORD_MAX_LENGTH
                )
            )
        )

        confirmPassword?.let {
            if (it != password) return Resource.Error(
                uiText = resourcesService.getString(Res.string.password_not_match_error)
            )
        }

        return Resource.Success(Unit)
    }
}