package presentation

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.services.ResourcesService
import domain.services.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow

class SummaryScreenModel(
    private val resourcesService: ResourcesService,
    private val settingsService: SettingsService
): BaseModel() {

    val streakText = MutableStateFlow<String?>(null)

    init {
        settingsService.getUser()?.let {
            streakText.value = resourcesService.getString(
                resource = SharedRes.strings.days,
                args = listOf(it.logStreak ?: 1)
            )
        }
    }
}