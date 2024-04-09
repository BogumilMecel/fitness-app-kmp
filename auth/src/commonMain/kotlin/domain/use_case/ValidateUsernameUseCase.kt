package domain.use_case

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.model.ResourceProvider
import utils.Resource

class ValidateUsernameUseCase(private val resourceProvider: ResourceProvider) {
    operator fun invoke(username: String): Resource<Unit> {
        if (username.isEmpty()) return Resource.Error(
            uiText = resourceProvider.getString(SharedRes.strings.empty_fields_error)
        )

        return Resource.Success(Unit)
    }
}