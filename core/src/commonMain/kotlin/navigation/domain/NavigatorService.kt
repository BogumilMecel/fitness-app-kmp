package domain

import kotlinx.coroutines.channels.Channel
import navigation.domain.NavigationAction

interface NavigatorService {
    val navigationAction: Channel<NavigationAction>
}