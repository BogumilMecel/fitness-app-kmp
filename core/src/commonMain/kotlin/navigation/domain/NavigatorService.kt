package navigation.domain

import kotlinx.coroutines.channels.Channel

interface NavigatorService {
    val navigationAction: Channel<NavigationAction>
    val backResult: Channel<Any>
}