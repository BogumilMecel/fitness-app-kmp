package navigation.domain

import kotlinx.coroutines.channels.Channel

interface SnackbarService {
    val message: Channel<String>
}