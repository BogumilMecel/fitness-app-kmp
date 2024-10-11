package di

import org.koin.dsl.module
import presentation.AccountScreenModel

val accountModule = module {
    factory { AccountScreenModel() }
}