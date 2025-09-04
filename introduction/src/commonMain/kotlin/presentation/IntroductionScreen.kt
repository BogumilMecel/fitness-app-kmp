package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.introduction.composeResources.Res
import com.gmail.bogumilmecel2.introduction.composeResources.back
import com.gmail.bogumilmecel2.introduction.composeResources.finish
import com.gmail.bogumilmecel2.introduction.composeResources.next
import components.Button
import components.LargeButtonTextContent
import domain.model.QuestionName
import domain.model.QuestionType
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import presentation.components.QuestionSection
import presentation.components.TextQuestion
import presentation.components.TilesQuestion
import theme.FitnessAppTheme

@Composable
fun IntroductionScreen(
    state: IntroductionState,
    onEvent: (IntroductionEvent) -> Unit,
) {
    val questionSize = QuestionName.entries.size
    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(initialPage = 0) {
        questionSize
    }
    val focusRequesters = remember {
        List(questionSize) { FocusRequester() }
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    var arrowState by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            arrowState = when (pagerState.currentPage) {
                0 -> 0
                else -> 2
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { index ->
            val currentItem = QuestionName.entries[index]
            QuestionSection(title = stringResource(currentItem.getQuestionTitle())) {
                when (currentItem.getQuestionType()) {
                    QuestionType.INPUT -> {
                        state.getStringAnswer(currentItem)?.let { answer ->
                            TextQuestion(
                                text = answer,
                                onTextEntered = {
                                    onEvent(
                                        IntroductionEvent.EnteredAnswer(
                                            questionName = currentItem,
                                            newValue = it
                                        )
                                    )
                                },
                                unitResId = currentItem.getQuestionUnit(),
                                focusRequester = focusRequesters[index],
                                tag = currentItem.name
                            )
                        }
                    }

                    QuestionType.TILE -> {
                        state.getTileAnswer(questionName = currentItem)?.let { selectedTile ->
                            TilesQuestion(
                                questionName = currentItem,
                                currentItem = selectedTile,
                                onItemClick = { clickedTile ->
                                    onEvent(
                                        IntroductionEvent.ClickedTile(
                                            tile = clickedTile
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }

        if (arrowState != 0) {
            Button(
                backgroundColor = FitnessAppTheme.colors.primary,
                onClick = {
                    coroutineScope.launch {
                        if (pagerState.currentPage != 0) {
                            val previousPageIndex = pagerState.currentPage - 1
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            if (QuestionName.entries.getOrNull(previousPageIndex)?.getQuestionType() == QuestionType.TILE) {
                                keyboardController?.hide()
                            }
                            if (QuestionName.entries.getOrNull(previousPageIndex)?.getQuestionType() == QuestionType.INPUT) {
                                focusRequesters.getOrNull(previousPageIndex)?.requestFocus()
                                keyboardController?.show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                LargeButtonTextContent(
                    text = stringResource(Res.string.back),
                    horizontalPadding = 24.dp,
                )
            }
        }

        Button(
            backgroundColor = FitnessAppTheme.colors.primary,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage != questionSize - 1) {
                        val nextPageIndex = pagerState.currentPage + 1
                        pagerState.animateScrollToPage(nextPageIndex)
                        if (QuestionName.entries.getOrNull(nextPageIndex)?.getQuestionType() == QuestionType.TILE) {
                            keyboardController?.hide()
                        }
                        if (QuestionName.entries.getOrNull(nextPageIndex)?.getQuestionType() == QuestionType.INPUT) {
                            focusRequesters.getOrNull(nextPageIndex)?.requestFocus()
                            keyboardController?.show()
                        }
                    } else {
                        onEvent(IntroductionEvent.FinishIntroduction)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            LargeButtonTextContent(
                text = stringResource(
                    if (pagerState.currentPage == questionSize - 1) {
                        Res.string.finish
                    } else {
                        Res.string.next
                    }
                ),
                horizontalPadding = 24.dp,
            )
        }
    }
} 