package presentation

import domain.model.QuestionName
import domain.model.Tile

sealed interface IntroductionEvent {
    data class EnteredAnswer(val questionName: QuestionName, val newValue: String): IntroductionEvent
    data class ClickedTile(val tile: Tile): IntroductionEvent
    object FinishIntroduction : IntroductionEvent
}