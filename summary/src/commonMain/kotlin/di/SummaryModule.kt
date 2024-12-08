package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.SummaryScreenModel

val summaryModule = module {
    viewModel { SummaryScreenModel() }
}