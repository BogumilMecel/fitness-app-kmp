package domain.use_case

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.constants.AuthConstants
import domain.model.ResourceProvider
import utils.Resource
import utils.isLengthInRange

class ValidatePasswordUseCase(private val resourceProvider: ResourceProvider) {
    operator fun invoke(
        password: String,
        confirmPassword: String? = null
    ): Resource<Unit> {
        if (password.isEmpty()) return Resource.Error(
            uiText = resourceProvider.getString(SharedRes.strings.empty_fields_error)
        )

        if (!password.isLengthInRange(
                minimum = AuthConstants.PASSWORD_MIN_LENGTH,
                maximum = AuthConstants.PASSWORD_MAX_LENGTH
            )
        ) return Resource.Error(
            uiText = resourceProvider.getString(
                resource = SharedRes.strings.password_length_error,
                args = listOf(
                    AuthConstants.PASSWORD_MIN_LENGTH,
                    AuthConstants.PASSWORD_MAX_LENGTH
                )
            )
        )

        confirmPassword?.let {
            if (it != password) return Resource.Error(
                uiText = resourceProvider.getString(SharedRes.strings.password_not_match_error)
            )
        }

        return Resource.Success(Unit)
    }
}