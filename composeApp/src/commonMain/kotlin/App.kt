import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.datetime.LocalDate
import main_screen.presentation.TabNavigationItem
import navigation.ModalBottomSheetLayout
import navigation.bottomSheet
import navigation.domain.NavigationAction
import navigation.domain.NavigatorService
import navigation.presentation.Route
import navigation.rememberBottomSheetNavigator
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import presentation.AccountScreen
import presentation.DiaryScreen
import presentation.SplashScreen
import presentation.SplashScreenModel
import presentation.SummaryScreen
import presentation.TrainingScreen
import presentation.login.LoginScreen
import presentation.navigation_screen.AuthNavigationScreen
import presentation.new_product.NewProductScreen
import presentation.new_product.NewProductScreenModel
import presentation.product.ProductScreen
import presentation.product.ProductScreenModel
import presentation.register.RegisterScreen
import presentation.search.DiarySearchScreen
import presentation.search.DiarySearchScreenModel
import theme.FitnessAppTheme

@Composable
fun App() {
    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {

        val navigatorService = koinInject<NavigatorService>()
        val bottomSheetNavigator = rememberBottomSheetNavigator(skipPartiallyExpanded = true)
        val navController = rememberNavController(bottomSheetNavigator)

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

        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            bottomSheetNavigator = bottomSheetNavigator,
            dragHandle = null,
        ) {
            Scaffold(
                bottomBar = {
                    if (navigationBarVisible) {
                        navBackStackEntry?.let {
                            NavigationBar(
                                containerColor = FitnessAppTheme.colors.backgroundSecondary,
                                modifier = Modifier.height(60.dp)
                            ) {
                                Route.BottomNavigation.entries.forEach { bottomNavigationRoute ->
                                    TabNavigationItem(
                                        bottomNavigation = bottomNavigationRoute,
                                        isSelected = it.destination.hasRoute(bottomNavigationRoute::class),
                                        onClick = {
                                            navController.navigate(bottomNavigationRoute)
                                        }
                                    )
                                }
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
                        val route = it.toRoute<Route.DiarySearch>()
                        val viewModel: DiarySearchScreenModel = koinViewModel {
                            parametersOf(LocalDate.parse(route.date), route.mealName)
                        }
                        val state by viewModel.state.collectAsState()

                        DiarySearchScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    composable<Route.NewProduct> {
                        val viewModel: NewProductScreenModel = koinViewModel()
                        val state by viewModel.state.collectAsState()

                        NewProductScreen(
                            state = state,
                            onEvent = viewModel::onEvent
                        )
                    }
                    bottomSheet<Route.AddProductDiaryEntry> {
                        val route = it.toRoute<Route.AddProductDiaryEntry>()
                        val viewModel: ProductScreenModel = koinViewModel {
                            parametersOf(
                                route.productId,
                                LocalDate.parse(route.date),
                                route.mealName
                            )
                        }
                        val state by viewModel.state.collectAsState()

                        ProductScreen(
                            state = state,
                            onEvent = viewModel::onEvent,
                        )
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
}