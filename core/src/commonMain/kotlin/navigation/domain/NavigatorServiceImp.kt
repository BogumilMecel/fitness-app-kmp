package domain

import kotlinx.coroutines.channels.Channel
import navigation.domain.NavigationAction
import navigation.domain.NavigatorService

class NavigatorServiceImp : NavigatorService {
    override val navigationAction: Channel<NavigationAction> = Channel()
}