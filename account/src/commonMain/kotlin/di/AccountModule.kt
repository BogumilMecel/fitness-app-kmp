package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.AccountScreenModel

val accountModule = module {
    viewModel { AccountScreenModel() }
}