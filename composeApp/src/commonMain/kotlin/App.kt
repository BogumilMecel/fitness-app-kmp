
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import models.Product
import navigation.ModalBottomSheetLayout
import navigation.domain.NavigationAction
import navigation.domain.NavigatorService
import navigation.domain.SnackbarService
import navigation.presentation.Route
import navigation.rememberBottomSheetNavigator
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import presentation.AccountScreen
import presentation.DiaryScreen
import presentation.IntroductionScreen
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
import presentation.selector.SelectorScreen
import presentation.selector.SelectorScreenModel
import theme.FitnessAppTheme
import utils.LocalDateParameterType
import utils.ProductParameterType
import utils.SelectorItemListParameterType
import utils.bottomSheet
import kotlin.reflect.typeOf

@Composable
fun App() {
    FitnessAppTheme(darkTheme = isSystemInDarkTheme()) {

        val navigatorService = koinInject<NavigatorService>()
        val snackbarService = koinInject<SnackbarService>()
        val bottomSheetNavigator = rememberBottomSheetNavigator(skipPartiallyExpanded = true)
        val navController = rememberNavController(bottomSheetNavigator)
        val snackbarHostState = remember { SnackbarHostState() }

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val navigationBarVisible = navBackStackEntry?.destination?.let { currentDestination ->
            currentDestination.hasRoute(Route.BottomNavigation.Summary::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Diary::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Training::class)
                    || currentDestination.hasRoute(Route.BottomNavigation.Account::class)
        } == true

        val backgroundColor = if (navBackStackEntry?.destination?.hasRoute(Route.DiarySearch::class) == true) {
            FitnessAppTheme.colors.backgroundSecondary
        } else {
            FitnessAppTheme.colors.background
        }

        LaunchedEffect(true) {
            navigatorService.navigationAction.receiveAsFlow().collect {
                when (it) {
                    is NavigationAction.ToScreen -> {
                        navController.navigate(it.route) {
                            it.popUpParams?.let { popUpParams ->
                                popUpTo(route = popUpParams.popUpTo) {
                                    inclusive = popUpParams.inclusive
                                }
                            }
                        }
                    }

                    is NavigationAction.Back -> {
                        if (it.withPopUp) {
                            navController.popBackStack()
                        } else {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }

        LaunchedEffect(snackbarService) {
            snackbarService.message.receiveAsFlow().collect { message ->
                snackbarHostState.showSnackbar(message)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            ModalBottomSheetLayout(
                modifier = Modifier.fillMaxSize(),
                bottomSheetNavigator = bottomSheetNavigator,
                containerColor = FitnessAppTheme.colors.backgroundSecondary,
                contentColor = FitnessAppTheme.colors.contentPrimary,
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
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
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
                        composable<Route.Introduction> {
                            IntroductionScreen()
                        }
                        composable<Route.BottomNavigation.Summary> {
                            SummaryScreen()
                        }
                        composable<Route.BottomNavigation.Diary> {
                            DiaryScreen()
                        }
                        composable<Route.DiarySearch>(
                            typeMap = mapOf(
                                typeOf<LocalDate>() to LocalDateParameterType,
                            )
                        ) {
                            val route = it.toRoute<Route.DiarySearch>()
                            val viewModel: DiarySearchScreenModel = koinViewModel {
                                parametersOf(route.date, route.mealName)
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
                        composable<Route.AddProductDiaryEntry>(
                            typeMap = mapOf(
                                typeOf<Product>() to ProductParameterType,
                                typeOf<LocalDate>() to LocalDateParameterType,
                            ),
                        ) {
                            val route = it.toRoute<Route.AddProductDiaryEntry>()
                            val viewModel: ProductScreenModel = koinViewModel {
                                parametersOf(
                                    route.product,
                                    route.date,
                                    route.mealName,
                                    route.weight,
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
                        bottomSheet<Route.Selector>(
                            navigator = bottomSheetNavigator,
                            typeMap = mapOf(
                                typeOf<List<domain.model.SelectorItem>>() to SelectorItemListParameterType,
                            )
                        ) {
                            val route = it.toRoute<Route.Selector>()
                            val viewModel: SelectorScreenModel = koinViewModel {
                                parametersOf(route.title, route.items)
                            }
                            val state by viewModel.state.collectAsState()

                            SelectorScreen(
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}