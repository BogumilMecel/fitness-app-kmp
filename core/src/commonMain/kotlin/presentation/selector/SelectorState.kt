package presentation.selector

import domain.model.SelectorItem

data class SelectorState(
    val title: String,
    val items: List<SelectorItem>,
) 