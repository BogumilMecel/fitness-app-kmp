package di

import org.koin.dsl.module
import presentation.SummaryScreenModel

val summaryModule = module {
    factory { SummaryScreenModel() }
}