package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import presentation.utils.getDefaultRootModifier

@Composable
fun AccountScreen(model: AccountScreenModel = koinViewModel()) {
    Column(getDefaultRootModifier()) {

    }
}