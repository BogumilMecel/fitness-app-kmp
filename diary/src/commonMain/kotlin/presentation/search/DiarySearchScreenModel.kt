package presentation.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import models.MealName
import models.Product
import navigation.presentation.Route
import presentation.base.BaseModel
import repository.DiaryRepository
import utils.constans.Constants

class DiarySearchScreenModel(
    private val date: LocalDate,
    private val mealName: MealName,
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    private var everythingRequestJob: Job? = null
    private var everythingPage = 0
    private val everythingProducts = mutableListOf<Product>()
    private var userProductsPage = 0
    private val userProducts = mutableListOf<Product>()

    val state = MutableStateFlow(
        value = DiarySearchState(
            date = date,
            mealName = mealName,
        )
    )

    init {
        // TODO: Request initial products
        state.update { it.copy(listState = ListState.Results(items = emptyList())) }
    }

    fun onEvent(event: DiarySearchEvent) {
        when (event) {
            DiarySearchEvent.BackPressed -> {
                onBackPressed()
            }

            DiarySearchEvent.ScrollToEnd -> {
                onScrollToEnd()
            }

            DiarySearchEvent.Search -> {
                onSearch()
            }

            DiarySearchEvent.AddProduct -> {
                onAddProductClicked()
            }

            DiarySearchEvent.ScanBarcode -> {
                onScanBarcodeClicked()
            }

            is DiarySearchEvent.SearchTextChanged -> {
                onSearchTextChanged(text = event.text)
            }

            is DiarySearchEvent.TabSelected -> {
                onTabSelected(tab = event.tab)
            }

            is DiarySearchEvent.ProductClicked -> {
                onProductClicked(product = event.product)
            }
        }
    }

    private fun onSearchTextChanged(text: String) {
        state.update {
            it.copy(searchBarText = text)
        }
    }

    private fun onSearch() {
        everythingPage = 1
        requestEverythingProducts()
    }

    private fun onTabSelected(tab: SearchTab) {
        state.update {
            it.copy(selectedTab = tab)
        }
        requestOrAssignUserProductsIfNeeded()
    }

    private fun onAddProductClicked() {
        navigateTo(Route.NewProduct)
    }

    private fun onScanBarcodeClicked() {
        // TODO: Handle on scan barcode clicked
    }

    private fun onProductClicked(product: Product) {
        navigateTo(
            Route.AddProductDiaryEntry(
                product = product,
                date = date,
                mealName = mealName,
                weight = 100,
            )
        )
    }

    private fun requestOrAssignUserProductsIfNeeded() {
        if (state.value.selectedTab == SearchTab.PRODUCTS) {
            if (userProductsPage == 0) {
                userProductsPage = 1
                requestUserOfflineProducts()
            } else {
                assignUserProducts()
            }
        }
    }

    private fun onScrollToEnd() {
        when (state.value.selectedTab) {
            SearchTab.EVERYTHING -> {
                if (everythingRequestJob != null) {
                    everythingPage++
                    requestEverythingProducts()
                }
            }

            SearchTab.PRODUCTS -> {
                userProductsPage++
                requestUserOfflineProducts()
            }

            SearchTab.RECIPES -> {

            }
        }
    }

    private fun requestEverythingProducts() {
        everythingRequestJob = viewModelScope.launch {
            runCatching {
                diaryRepository.searchForProducts(
                    searchText = state.value.searchBarText,
                    page = everythingPage,
                )
            }.onSuccess {
                everythingProducts += it.results
                assignEverythingState()
            }
            everythingRequestJob = null
        }
    }

    private fun assignUserProducts() {
        viewModelScope.launch {
            state.update {
                it.copy(userProductsState = ListState.Results(items = userProducts))
            }
        }
    }

    private fun assignEverythingState() {
        viewModelScope.launch {
            state.update {
                it.copy(listState = ListState.Results(items = everythingProducts))
            }
        }
    }

    private fun requestUserOfflineProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            userProducts += diaryRepository.getOfflineProducts(
                userId = settingsService.getNotNullUser().id,
                searchText = state.value.searchBarText,
                limit = Constants.Paging.OFFLINE_PAGE_SIZE,
                skip = userProductsPage * Constants.Paging.OFFLINE_PAGE_SIZE,
            )
            assignUserProducts()
        }
    }
}