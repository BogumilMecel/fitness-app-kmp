package presentation.search

import constans.Constants
import domain.repository.DiaryRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import presentation.base.BaseModel
import string.EMPTY

class DiarySearchScreenModel(
    private val diaryRepository: DiaryRepository,
) : BaseModel() {

    val searchText = MutableStateFlow(String.EMPTY)
    val selectedTab = MutableStateFlow(SearchTab.EVERYTHING)

    private val userProductsPage = MutableStateFlow(0)
    private val userRecipesPage = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val userProducts = combine(
        searchText,
        selectedTab,
        userProductsPage,
        settingsService.getUser()
    ) { searchText, selectedTab, page, user ->
        if (selectedTab == SearchTab.PRODUCTS) {
            diaryRepository.getOfflineProducts(
                userId = user?.id,
                searchText = searchText,
                limit = Constants.Paging.OFFLINE_PAGE_SIZE,
                skip = page * Constants.Paging.OFFLINE_PAGE_SIZE,
            )
        } else emptyFlow()
    }.flatMapLatest { it }.stateInScreenModelScope(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val userRecipes = combine(
        searchText,
        selectedTab,
        userRecipesPage,
        settingsService.getUser()
    ) { searchText, selectedTab, page, user ->
        if (selectedTab == SearchTab.RECIPES) {
            diaryRepository.getOfflineRecipes(
                userId = user?.id,
                searchText = searchText,
                limit = Constants.Paging.OFFLINE_PAGE_SIZE,
                skip = page * Constants.Paging.OFFLINE_PAGE_SIZE,
            )
        } else emptyFlow()
    }.flatMapLatest { it }.stateInScreenModelScope(emptyList())

    fun onSearchTextChanged(text: String) {
        searchText.update { text }
    }

    fun onSearch() {

    }

    fun onTabSelected(tab: SearchTab) {
        selectedTab.value = tab
    }
}