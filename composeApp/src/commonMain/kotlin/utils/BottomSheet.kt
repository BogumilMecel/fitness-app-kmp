package utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import navigation.BottomSheetNavigator
import navigation.BottomSheetNavigatorDestinationBuilder
import kotlin.jvm.JvmSuppressWildcards
import kotlin.reflect.KType

inline fun <reified T : Any> NavGraphBuilder.bottomSheet(
    deepLinks: List<NavDeepLink> = emptyList(),
    navigator: BottomSheetNavigator,
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>>,
    noinline content: @Composable ColumnScope.(backstackEntry: NavBackStackEntry) -> Unit
) {
    destination(
        BottomSheetNavigatorDestinationBuilder(
            navigator,
            T::class,
            typeMap,
            content
        ).apply {
            deepLinks.forEach { deepLink -> deepLink(deepLink) }
        }
    )
}