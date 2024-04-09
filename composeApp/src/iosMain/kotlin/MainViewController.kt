import androidx.compose.ui.window.ComposeUIViewController
import di.iosSharedModule
import di.startDi

fun MainViewController() = ComposeUIViewController {
    startDi(nativeModule = iosSharedModule)
    App()
}