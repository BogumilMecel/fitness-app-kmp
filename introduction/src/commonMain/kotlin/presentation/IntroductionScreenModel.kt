package presentation

import androidx.lifecycle.viewModelScope
import domain.model.ActivityLevel
import domain.model.DesiredWeight
import domain.model.Gender
import domain.model.QuestionName
import domain.model.TrainingFrequency
import domain.model.TypeOfWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.presentation.Route
import presentation.base.BaseModel

class IntroductionScreenModel : BaseModel() {

    val state = MutableStateFlow(IntroductionState())

    fun onEvent(event: IntroductionEvent) {
        when (event) {
            is IntroductionEvent.ClickedTile -> {
                when (event.tile) {
                    is Gender -> state.update {
                        it.copy(selectedGender = event.tile)
                    }

                    is ActivityLevel -> state.update {
                        it.copy(activityLevel = event.tile)
                    }

                    is TrainingFrequency -> state.update {
                        it.copy(trainingFrequency = event.tile)
                    }

                    is TypeOfWork -> state.update {
                        it.copy(typeOfWork = event.tile)
                    }

                    is DesiredWeight -> state.update {
                        it.copy(desiredWeight = event.tile)
                    }
                }
            }

            is IntroductionEvent.EnteredAnswer -> {
                when (event.questionName) {
                    QuestionName.AGE -> state.update {
                        it.copy(age = event.newValue)
                    }

                    QuestionName.HEIGHT -> state.update {
                        it.copy(height = event.newValue)
                    }

                    QuestionName.CURRENT_WEIGHT -> state.update {
                        it.copy(currentWeight = event.newValue)
                    }

                    else -> {}
                }
            }

            is IntroductionEvent.FinishIntroduction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    runCatching {
                        // TODO: Save introduction information
                        // saveIntroductionInformation(state.value)
                    }.onSuccess {
                        navigateTo(
                            route = Route.BottomNavigation.Summary,
                            popUpTo = Route.Introduction,
                        )
                    }.onFailure {
                        // TODO: Handle error
                    }
                }
            }
        }
    }
} 