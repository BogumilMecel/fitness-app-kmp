package presentation

import com.gmail.bogumilmecel2.ui.SharedRes
import domain.services.ResourcesService
import domain.services.SettingsService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class SummaryScreenModel(
    private val resourcesService: ResourcesService,
    settingsService: SettingsService
): BaseModel() {

    private val currentCalories = MutableStateFlow<Int?>(null)
    private val wantedCalories = MutableStateFlow<Int?>(null)

    val streakText = MutableStateFlow<String?>(null)

    val caloriesData = combine(
        flow = currentCalories,
        flow2 = wantedCalories
    ) { currentCalories, wantedCalories ->
        if (currentCalories != null && wantedCalories != null) {
            val progress = currentCalories.toFloat() / wantedCalories.toFloat()
            CaloriesData(
                currentCalories = currentCalories,
                wantedCalories = wantedCalories,
                progress = progress,
                progressText = "${(progress * 100.0).toInt()}" + "%"
            )
        } else null
    }.collectInScreenModel(null)

    init {
        settingsService.getUser()?.let {
            streakText.value = resourcesService.getString(
                resource = SharedRes.strings.days,
                args = listOf(it.logStreak)
            )
            wantedCalories.value = it.nutritionValues?.calories
            // TODO: Replace with value from database
            currentCalories.value = 212
        }
    }
}

data class CaloriesData(
    val currentCalories: Int,
    val wantedCalories: Int,
    val progress: Float,
    val progressText: String
)