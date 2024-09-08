/*
package domain.use_case

import domain.services.SettingsService
import utils.Resource

class CheckIfShouldShowWeightPickerUseCase(private val settingsService: SettingsService) {
    suspend operator fun invoke(): Resource<Unit> {
        val lastTimeWeightDialogsShown = settingsService.getUser()?.
        val currentDate = CustomDateUtils.getDate()

        if (lastTimeWeightDialogsShown == currentDate) return Resource.Error()

        val user = cachedValuesProvider.getUser() ?: return Resource.Error()

        if (user.askForWeightDaily != true) return Resource.Error()

        if (user.latestWeightEntry?.date == CustomDateUtils.getDate()) return Resource.Error()

        cachedValuesProvider.setLocalLastTimeShowedWeightPicker(date = currentDate)

        return Resource.Success(Unit)
    }
}*/
