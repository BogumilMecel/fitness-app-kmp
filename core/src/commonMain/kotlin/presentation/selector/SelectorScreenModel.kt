package presentation.selector

import domain.model.SelectorItem
import kotlinx.coroutines.flow.MutableStateFlow
import presentation.base.BaseModel

class SelectorScreenModel(
    private val title: String,
    private val items: List<SelectorItem>
) : BaseModel() {

    val state = MutableStateFlow(
        value = SelectorState(
            title = title,
            items = items
        )
    )

    fun onEvent(event: SelectorEvent) {
        when (event) {
            is SelectorEvent.OnItemSelected -> {
                onItemSelected(event.item)
            }
            
            SelectorEvent.OnBackPressed -> {
                onBackPressed()
            }
        }
    }

    private fun onItemSelected(selectedItem: SelectorItem) {
        setBackResult(selectedItem)
        onBackPressed(withPopUp = true)
    }
} 