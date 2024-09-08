package presentation

import kotlinx.coroutines.flow.map

class SummaryScreenModel : BaseModel() {

    val logStreak = getNotNullUserFlow().map { it.logStreak }.stateInScreenModelScope(initialValue = 1)

}