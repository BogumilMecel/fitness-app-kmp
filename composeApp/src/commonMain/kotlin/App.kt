
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.login.LoginScreen
import presentation.login.LoginScreenModel
import presentation.register.RegisterScreenModel
import theme.FitnessAppTheme

@Composable
fun App() {
    initKoin()

    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {
        Navigator(
            LoginScreen()
        )
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
}