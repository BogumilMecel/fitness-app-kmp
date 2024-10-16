package main_screen.presentation

import cafe.adriel.voyager.core.model.screenModelScope
import domain.repository.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import presentation.base.BaseModel

class TabNavigatorModel(private val mainRepository: MainRepository) : BaseModel() {

    val tabNavigationEnabled = MutableStateFlow(false)

    init {
        requestInitialData()
    }

    private fun requestInitialData() {
        screenModelScope.launch {
            val availableDiaryDates = async { requestAvailableDiaryDates() }
            awaitAll(availableDiaryDates)
            tabNavigationEnabled.value = true
        }
    }

    private suspend fun requestAvailableDiaryDates() {
        mainRepository.requestAvailableDates().handle {
            settingsService.setAvailableDiaryDatesCount(it.availableDaysCount)
        }
    }
}