
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import presentation.SplashScreen
import theme.FitnessAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {
        BottomSheetNavigator {
            Navigator(screen = SplashScreen())
        }
    }
}