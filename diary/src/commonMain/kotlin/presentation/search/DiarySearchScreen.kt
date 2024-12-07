package presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.gmail.bogumilmecel2.ui.composeResources.Res
import com.gmail.bogumilmecel2.ui.composeResources.search_for_products
import components.FitnessAppLazyColumn
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.HorizontalSpacer
import date.getDisplayValue
import domain.model.MealName
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import presentation.ModelLayout
import presentation.components.DiaryItem
import presentation.utils.getDefaultRootModifier
import theme.FitnessAppTheme

class DiarySearchScreen(
    private val mealName: MealName,
    private val date: LocalDate,
) : Screen {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        ModelLayout<DiarySearchScreenModel> {
            val searchBarText by searchText.collectAsState()
            val selectedTab by selectedTab.collectAsState()
            val productsParams by productsParams.collectAsState()

            val pagerState = rememberPagerState {
                SearchTab.entries.size
            }

            Column(modifier = getDefaultRootModifier()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = FitnessAppTheme.colors.backgroundSecondary)
                ) {
                    FitnessAppTopBar(
                        title = mealName.name,
                        subTitle = date.getDisplayValue(),
                        onBackPressed = ::onBackPressed
                    )

                    HorizontalSpacer(size = 8.dp)

                    FitnessAppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = searchBarText,
                        onValueChange = ::onSearchTextChanged,
                        label = stringResource(Res.string.search_for_products),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearch()
                            }
                        )
                    )

                    HorizontalSpacer(size = 16.dp)

                    TabRow(
                        selectedTabIndex = selectedTab.ordinal,
                        backgroundColor = FitnessAppTheme.colors.backgroundSecondary,
                        indicator = { tabPositions ->
                            Box(
                                modifier = Modifier
                                    .tabIndicatorOffset(tabPositions[selectedTab.ordinal])
                                    .fillMaxWidth()
                                    .height(2.dp)
                                    .background(FitnessAppTheme.colors.contentPrimary)
                            )
                        },
                        tabs = {
                            SearchTab.entries.forEachIndexed { index, tab ->
                                Tab(
                                    text = {
                                        Text(
                                            text = tab.displayValue,
                                            style = MaterialTheme.typography.button,
                                            color = FitnessAppTheme.colors.contentPrimary
                                        )
                                    },
                                    selected = index == selectedTab.ordinal,
                                    onClick = {
                                        onTabSelected(tab)
                                    }
                                )
                            }
                        }
                    )
                }

                HorizontalPager(state = pagerState) {
                    FitnessAppLazyColumn(onScrollToEnd = ::onScrollToEnd) {
                        itemsIndexed(productsParams) { index, product ->
                            DiaryItem(
                                params = product,
                                onItemClick = {
                                    
                                },
                                onItemLongClick = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }
}