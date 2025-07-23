package navigation.domain

import kotlinx.coroutines.channels.Channel

class SnackbarServiceImp : SnackbarService {
    override val message: Channel<String> = Channel()
}