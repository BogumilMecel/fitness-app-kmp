package presentation.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.barcode_scan
import com.gmail.bogumilmecel2.diary.composeResources.new_product
import com.gmail.bogumilmecel2.diary.composeResources.scan_barcode
import com.gmail.bogumilmecel2.diary.composeResources.search_for_products
import components.Button
import components.FitnessAppTextField
import components.FitnessAppTopBar
import components.HorizontalSpacer
import components.LazyColumn
import components.VerticalSpacer
import date.getDisplayValue
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.components.DiaryItem
import presentation.utils.getDefaultRootModifier
import theme.FitnessAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiarySearchScreen(
    state: DiarySearchState,
    onEvent: (DiarySearchEvent) -> Unit,
) {
    val everythingListState = rememberLazyListState()
    val userProductsListState = rememberLazyListState()
    val userRecipesListState = rememberLazyListState()

    Column(modifier = getDefaultRootModifier()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = FitnessAppTheme.colors.backgroundSecondary)
        ) {
            FitnessAppTopBar(
                title = state.mealName.displayName,
                subTitle = state.date.getDisplayValue(),
                onBackPressed = {
                    onEvent(DiarySearchEvent.BackPressed)
                },
            )

            VerticalSpacer(size = 8.dp)

            FitnessAppTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = state.searchBarText,
                onValueChange = {
                    onEvent(DiarySearchEvent.SearchTextChanged(it))
                },
                label = stringResource(Res.string.search_for_products),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onEvent(DiarySearchEvent.Search)
                    }
                )
            )

            VerticalSpacer(size = 16.dp)

            TabRow(
                selectedTabIndex = state.selectedTab.ordinal,
                containerColor = FitnessAppTheme.colors.backgroundSecondary,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[state.selectedTab.ordinal])
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
                                    style = FitnessAppTheme.typography.labelMedium,
                                    color = FitnessAppTheme.colors.contentPrimary
                                )
                            },
                            selected = index == state.selectedTab.ordinal,
                            onClick = {
                                onEvent(DiarySearchEvent.TabSelected(tab = tab))
                            }
                        )
                    }
                }
            )
        }

        ListContent(
            listState = state.listState,
            lazyListState = when (state.selectedTab) {
                SearchTab.EVERYTHING -> everythingListState
                SearchTab.PRODUCTS -> userProductsListState
                SearchTab.RECIPES -> userRecipesListState
            },
            additionalContent = {
                when (state.selectedTab) {
                    SearchTab.EVERYTHING -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            SearchButton(
                                text = stringResource(Res.string.new_product),
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        tint = FitnessAppTheme.colors.contentSecondary,
                                        modifier = Modifier.size(20.dp),
                                    )
                                },
                                onClick = {
                                    onEvent(DiarySearchEvent.AddProduct)
                                }
                            )

                            SearchButton(
                                text = stringResource(Res.string.scan_barcode),
                                icon = {
                                    Icon(
                                        painter = painterResource(Res.drawable.barcode_scan),
                                        contentDescription = null,
                                        tint = FitnessAppTheme.colors.contentSecondary,
                                        modifier = Modifier.size(20.dp),
                                    )
                                },
                                onClick = {
                                    onEvent(DiarySearchEvent.AddProduct)
                                }
                            )
                        }
                    }
                    SearchTab.PRODUCTS -> {

                    }
                    SearchTab.RECIPES -> {

                    }
                }
            },
            onScrolledToEnd = {
                onEvent(DiarySearchEvent.ScrollToEnd)
            }
        )
    }
}

@Composable
private fun RowScope.SearchButton(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.weight(1f),
        backgroundColor = FitnessAppTheme.colors.backgroundSecondary,
        contentColor = FitnessAppTheme.colors.contentSecondary,
        onClick = onClick,
        borderStroke = BorderStroke(
            width = 1.dp,
            color = FitnessAppTheme.colors.contentSecondary,
        ),
        content = {
            icon()

            HorizontalSpacer(size = 4.dp)

            Text(
                text = text,
                style = FitnessAppTheme.typography.labelMedium,
                color = FitnessAppTheme.colors.contentSecondary,
                maxLines = 1
            )
        }
    )
}

@Composable
private fun ListContent(
    listState: ListState,
    lazyListState: LazyListState,
    additionalContent: @Composable () -> Unit,
    onScrolledToEnd: () -> Unit,
) {
    additionalContent()

    when (listState) {
        is ListState.Loading -> {}
        is ListState.Results -> {
            LazyColumn(
                onScrollToEnd = onScrolledToEnd,
                state = lazyListState,
            ) {
                itemsIndexed(listState.items) { index, product ->
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