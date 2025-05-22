import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import domain.NavigatorService
import kotlinx.coroutines.flow.receiveAsFlow
import main_screen.presentation.TabNavigationItem
import navigation.domain.NavigationAction
import navigation.presentation.Route
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import presentation.AccountScreen
import presentation.DiaryScreen
import presentation.DiarySearchScreen
import presentation.SplashScreen
import presentation.SplashScreenModel
import presentation.SummaryScreen
import presentation.TrainingScreen
import presentation.login.LoginScreen
import presentation.navigation_screen.AuthNavigationScreen
import presentation.register.RegisterScreen
import theme.FitnessAppTheme

@Composable
fun App() {
    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {

        val navigatorService = koinInject<NavigatorService>()
        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val navigationBarVisible = navBackStackEntry?.destination?.let { currentDestination ->
            currentDestination.hasRoute(Route.BottomNavigation.Summary::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Diary::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Training::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Account::class)
        } == true

        LaunchedEffect(true) {
            navigatorService.navigationAction.receiveAsFlow().collect {
                when (it) {
                    is NavigationAction.ToScreen -> {
                        navController.navigate(it.route)
                    }

                    is NavigationAction.Back -> {
                        navController.navigateUp()
                    }
                }
            }
        }

        Scaffold(
            bottomBar = {
                if (navigationBarVisible) {
                    navBackStackEntry?.let {
                        NavigationBar(
                            containerColor = FitnessAppTheme.colors.backgroundSecondary,
                            modifier = Modifier.height(60.dp)
                        ) {
                            TabNavigationItem(
                                bottomNavigation = Route.BottomNavigation.Summary,
                                navBackStackEntry = it,
                                onClick = {
                                    navController.navigate(Route.BottomNavigation.Summary)
                                }
                            )
                            TabNavigationItem(
                                bottomNavigation = Route.BottomNavigation.Diary,
                                navBackStackEntry = it,
                                onClick = {
                                    navController.navigate(Route.BottomNavigation.Diary)
                                }
                            )
                            TabNavigationItem(
                                bottomNavigation = Route.BottomNavigation.Training,
                                navBackStackEntry = it,
                                onClick = {
                                    navController.navigate(Route.BottomNavigation.Training)
                                }
                            )
                            TabNavigationItem(
                                bottomNavigation = Route.BottomNavigation.Account,
                                navBackStackEntry = it,
                                onClick = {
                                    navController.navigate(Route.BottomNavigation.Account)
                                }
                            )
                        }
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.Splash
            ) {
                composable<Route.Splash> {
                    koinViewModel<SplashScreenModel>()
                    SplashScreen()
                }
                composable<Route.AuthNavigator> {
                    AuthNavigationScreen()
                }
                composable<Route.Login> {
                    LoginScreen()
                }
                composable<Route.Register> {
                    RegisterScreen()
                }
                composable<Route.BottomNavigation.Summary> {
                    SummaryScreen()
                }
                composable<Route.BottomNavigation.Diary> {
                    DiaryScreen()
                }
                composable<Route.DiarySearch> {
                    DiarySearchScreen()
                }
                composable<Route.BottomNavigation.Training> {
                    TrainingScreen()
                }
                composable<Route.BottomNavigation.Account> {
                    AccountScreen()
                }
            }
        }
    }
}