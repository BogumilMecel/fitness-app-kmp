package presentation.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ProductScreen(
    model: ProductScreenModel,
    modifier: Modifier = Modifier
) {
    val state by model.state.collectAsState()
    
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // TODO: Implement UI
    }
}