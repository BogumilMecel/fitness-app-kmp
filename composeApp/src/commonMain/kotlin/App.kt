
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import presentation.SplashScreen
import theme.FitnessAppTheme

@Composable
fun App() {
    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {
        Navigator(screen = SplashScreen())
    }
}