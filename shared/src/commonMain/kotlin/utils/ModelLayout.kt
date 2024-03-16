package utils

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
inline fun <reified T : BaseModel> Screen.ModelLayout(
    model: @Composable T.() -> Unit
) {
    val navigator = LocalNavigator.current
    val screenModel = getScreenModel<T>()

    LaunchedEffect(true) {
        screenModel.navigation.receiveAsFlow().collect {
            navigator?.push(it)
        }
    }

    Box(modifier = getDefaultRootModifier()) {
        model(getScreenModel<T>())
    }
}