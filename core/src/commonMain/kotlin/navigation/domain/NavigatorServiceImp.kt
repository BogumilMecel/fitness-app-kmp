package navigation.domain

import kotlinx.coroutines.channels.Channel

class NavigatorServiceImp : NavigatorService {
    override val navigationAction: Channel<NavigationAction> = Channel()
    override val backResult: Channel<Any> = Channel()
}