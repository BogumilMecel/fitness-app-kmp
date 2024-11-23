package presentation.search

import components.TextFieldData
import kotlinx.coroutines.flow.MutableStateFlow
import presentation.base.BaseModel

class DiarySearchScreenModel : BaseModel() {

    val searchBarText = MutableStateFlow(TextFieldData())

    init {
        searchBarText.initTextField()
    }

    fun onSearch() {

    }
}