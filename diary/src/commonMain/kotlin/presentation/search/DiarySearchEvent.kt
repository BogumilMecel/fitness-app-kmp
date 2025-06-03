package presentation.search

sealed interface DiarySearchEvent {
    object BackPressed : DiarySearchEvent
    data class SearchTextChanged(val text: String) : DiarySearchEvent
    object Search : DiarySearchEvent
    data class TabSelected(val tab: SearchTab) : DiarySearchEvent
    object ScrollToEnd : DiarySearchEvent
}