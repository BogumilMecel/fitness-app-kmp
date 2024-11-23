package presentation.search

import components.TextFieldData
import kotlinx.coroutines.flow.MutableStateFlow
import presentation.base.BaseModel

class DiarySearchScreenModel : BaseModel() {

    val searchBarText = MutableStateFlow(TextFieldData())
    val selectedTabIndex = MutableStateFlow(SearchTab.EVERYTHING.ordinal)

    init {
        searchBarText.initTextField()
    }

    fun onSearch() {

    }

    fun onTabSelected(tab: SearchTab) {
        selectedTabIndex.value = tab.ordinal
    }
}