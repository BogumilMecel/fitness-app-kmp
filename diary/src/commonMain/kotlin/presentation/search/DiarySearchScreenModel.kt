package presentation.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import models.MealName
import models.Product
import models.Recipe
import navigation.presentation.Route
import presentation.base.BaseModel
import repository.DiaryRepository

@OptIn(ExperimentalCoroutinesApi::class)
class DiarySearchScreenModel(
    private val date: LocalDate,
    private val mealName: MealName,
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    private var everythingRequestJob: Job? = null
    private var everythingPage = 0
    private val everythingProducts = mutableListOf<Product>()

    val state = MutableStateFlow(
        value = DiarySearchState(
            date = date,
            mealName = mealName,
        )
    )

    init {
        state.filter { it.selectedTab == SearchTab.PRODUCTS }.map { state ->
            withContext(Dispatchers.IO) {
                diaryRepository.getOfflineProducts(
                    userId = settingsService.getNotNullUser().id,
                    searchText = state.searchBarText.ifEmpty { null }
                )
            }
        }.onEach { assignUserProducts(products = it) }.launchIn(viewModelScope)

        state.filter { it.selectedTab == SearchTab.RECIPES }.map { state ->
            withContext(Dispatchers.IO) {
                diaryRepository.getOfflineRecipes(
                    userId = settingsService.getNotNullUser().id,
                    searchText = state.searchBarText.ifEmpty { null },
                )
            }
        }.onEach { assignUserRecipes(recipes = it) }.launchIn(viewModelScope)
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

    private fun onScrollToEnd() {
        if (state.value.selectedTab == SearchTab.EVERYTHING) {
            if (everythingRequestJob != null) {
                everythingPage++
                requestEverythingProducts()
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

    private fun assignUserProducts(products: List<Product>) {
        viewModelScope.launch {
            state.update {
                it.copy(userProducts = products)
            }
        }
    }

    private fun assignUserRecipes(recipes: List<Recipe>) {
        viewModelScope.launch {
            state.update {
                it.copy(userRecipes = recipes)
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
}