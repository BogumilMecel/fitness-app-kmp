package domain

import kotlinx.coroutines.channels.Channel
import navigation.domain.NavigationAction

class NavigatorServiceImp : NavigatorService {
    override val navigationAction: Channel<NavigationAction> = Channel()
}