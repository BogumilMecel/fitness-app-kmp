import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.core.registry.ScreenRegistry
import di.createSharedNativeModule
import di.iosSharedModule
import di.startDi
import main_screen.presentation.TabNavigatorScreen
import org.koin.dsl.module
import presentation.navigation.SharedScreen
import presentation.navigation_screen.AuthNavigationScreen

@Suppress("unused", "FunctionName")
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