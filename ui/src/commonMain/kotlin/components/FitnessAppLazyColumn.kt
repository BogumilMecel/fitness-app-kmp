package components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun FitnessAppLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState,
    onScrollToEnd: (() -> Unit)?,
    content: LazyListScope.() -> Unit
) {
    LaunchedEffect(key1 = state.canScrollForward) {
        if (!state.canScrollForward) onScrollToEnd?.invoke()
    }

    LazyColumn(
        modifier = modifier,
        state = state,
        content = content
    )
}