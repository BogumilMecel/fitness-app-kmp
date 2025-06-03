package presentation.search

import androidx.lifecycle.viewModelScope
import constans.Constants
import domain.model.MealName
import domain.model.Product
import domain.repository.DiaryRepository
import domain.services.ResourcesService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import presentation.base.BaseModel
import presentation.components.DiaryItemParams

class DiarySearchScreenModel(
    private val date: LocalDate,
    private val mealName: MealName,
    private val diaryRepository: DiaryRepository,
    private val resourcesService: ResourcesService,
) : BaseModel() {

    private var userProductsPage = 0
    private val userProducts = mutableListOf<Product>()

    val state = MutableStateFlow(
        value = DiarySearchState(
            date = date,
            mealName = mealName,
        )
    )

    init {
        state
            .distinctUntilChangedBy { it.selectedTab }
            .filter { it.selectedTab == SearchTab.PRODUCTS }
            .onEach {
                if (userProductsPage == 0) {
                    userProductsPage = 1
                    requestUserOfflineProducts()
                } else {
                    assignUserProducts()
                }
            }.launchIn(viewModelScope)
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

            is DiarySearchEvent.SearchTextChanged -> {
                onSearchTextChanged(text = event.text)
            }

            is DiarySearchEvent.TabSelected -> {
                onTabSelected(tab = event.tab)
            }
        }
    }

    private fun onSearchTextChanged(text: String) {
        state.update {
            it.copy(searchBarText = text)
        }
    }

    private fun onSearch() {

    }

    private fun onTabSelected(tab: SearchTab) {
        state.update {
            it.copy(selectedTab = tab)
        }
    }

    private fun onScrollToEnd() {
        when (state.value.selectedTab) {
            SearchTab.EVERYTHING -> {

            }

            SearchTab.PRODUCTS -> {
                userProductsPage++
                requestUserOfflineProducts()
            }

            SearchTab.RECIPES -> {

            }
        }
    }

    private suspend fun assignUserProducts() {
        state.update {
            it.copy(
                productsParams = userProducts.map { product ->
                    DiaryItemParams.create(
                        product = product,
                        resourcesService = resourcesService,
                    )
                }
            )
        }
    }

    private fun requestUserOfflineProducts() {
        viewModelScope.launch {
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