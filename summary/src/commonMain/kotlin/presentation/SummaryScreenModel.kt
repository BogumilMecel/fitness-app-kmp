package presentation

import kotlinx.coroutines.flow.map
import presentation.base.BaseModel

class SummaryScreenModel : BaseModel() {

    val logStreak = getNotNullUserFlow().map { it.logStreak }.stateInScreenModelScope(initialValue = 1)

}