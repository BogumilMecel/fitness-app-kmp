package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import presentation.utils.getDefaultRootModifier

@Composable
fun TrainingScreen(model: TrainingScreenModel = koinViewModel()) {
    Column(getDefaultRootModifier()) {

    }
}