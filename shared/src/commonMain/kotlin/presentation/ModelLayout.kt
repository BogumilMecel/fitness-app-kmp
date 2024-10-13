package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.flow.receiveAsFlow
import presentation.base.BaseModel
import presentation.navigation.NavigationAction
import presentation.utils.getDefaultRootModifier

@Composable
inline fun <reified T : BaseModel> Screen.ModelLayout(
    model: @Composable T.() -> Unit
) {
    val navigator = LocalNavigator.current
    val screenModel = getScreenModel<T>()

    LaunchedEffect(true) {
        screenModel.navigation.receiveAsFlow().collect { navigationAction ->
            navigator?.let {
                when(navigationAction) {
                    is NavigationAction.ToScreen -> {
                        navigator.push(navigationAction.screen)
                    }

                    is NavigationAction.ToSharedScreen -> {
                        navigator.replaceAll(ScreenRegistry.get(navigationAction.screen))
                    }

                    is NavigationAction.Back -> {
                        navigator.pop()
                    }
                }
            }
        }
    }

    Box(modifier = getDefaultRootModifier()) {
        model(getScreenModel<T>())
    }
}