package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.IntroductionScreenModel

val introductionModule = module {
    viewModel<IntroductionScreenModel> { IntroductionScreenModel() }
} 