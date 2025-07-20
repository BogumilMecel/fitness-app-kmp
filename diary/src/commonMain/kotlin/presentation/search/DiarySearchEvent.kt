package presentation.search

import models.Product

sealed interface DiarySearchEvent {
    object BackPressed : DiarySearchEvent
    data class SearchTextChanged(val text: String) : DiarySearchEvent
    object Search : DiarySearchEvent
    object AddProduct : DiarySearchEvent
    object ScanBarcode : DiarySearchEvent
    data class TabSelected(val tab: SearchTab) : DiarySearchEvent
    object ScrollToEnd : DiarySearchEvent
    data class ProductClicked(val product: Product) : DiarySearchEvent
}