import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.core.registry.ScreenRegistry
import di.iosSharedModule
import di.startDi
import main_screen.presentation.TabNavigatorScreen
import presentation.navigation.SharedScreen
import presentation.navigation_screen.AuthNavigationScreen

fun MainViewController() = ComposeUIViewController {
    ScreenRegistry {
        register<SharedScreen.AuthNavigationScreen> {
            AuthNavigationScreen()
        }
        register<SharedScreen.TabNavigatorScreen> {
            TabNavigatorScreen()
        }
    }
    startDi(nativeModule = iosSharedModule)
    App()
}