package presentation.selector

import domain.model.SelectorItem

sealed interface SelectorEvent {
    data class OnItemSelected(val item: SelectorItem) : SelectorEvent
    data object OnBackPressed : SelectorEvent
} 