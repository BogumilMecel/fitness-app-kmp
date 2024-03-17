
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.login.LoginScreenModel
import presentation.navigation_screen.AuthNavigationModel
import presentation.navigation_screen.AuthNavigationScreen
import presentation.register.RegisterScreenModel
import theme.FitnessAppTheme

@Composable
fun App() {
    initKoin()

    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {
        Navigator(AuthNavigationScreen())
    }
}

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}

val sharedModule = module {
    factory { LoginScreenModel() }
    factory { RegisterScreenModel() }
    factory { AuthNavigationModel() }
}