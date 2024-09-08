/*
package domain.use_case

import com.gmail.bogumilmecel2.fitnessappv2.common.domain.provider.CachedValuesProvider
import com.gmail.bogumilmecel2.fitnessappv2.common.util.CustomDateUtils
import com.gmail.bogumilmecel2.fitnessappv2.common.util.Resource

class CheckIfShouldAskForWeightDialogsUseCase {
    suspend operator fun invoke(cachedValuesProvider: CachedValuesProvider): Resource<Unit> {
        val currentDate = CustomDateUtils.getDate()
        val user = cachedValuesProvider.getUser() ?: return Resource.Error()

        if (user.askForWeightDaily != null) return Resource.Error()

        val weightDialogsQuestion = cachedValuesProvider.getLocalWeightDialogsQuestion()

        if (weightDialogsQuestion != null) {
            if (weightDialogsQuestion.lastTimeAsked == currentDate) return Resource.Error()
            if (weightDialogsQuestion.askedCount > 3) return Resource.Error()
        }

        return Resource.Success(Unit)
    }
}*/
