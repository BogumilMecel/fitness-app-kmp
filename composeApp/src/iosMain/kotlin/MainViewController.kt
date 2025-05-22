
import androidx.compose.ui.window.ComposeUIViewController
import di.iosSharedModule
import di.startDi

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    startDi(nativeModule = iosSharedModule)
    App()
}