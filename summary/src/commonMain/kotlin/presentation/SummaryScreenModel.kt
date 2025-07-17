package presentation

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import presentation.base.BaseModel

class SummaryScreenModel : BaseModel() {

    val logStreak = settingsService.getUser().filterNotNull().map {
        it.logStreak
    }.stateInScreenModelScope(initialValue = 1)

}