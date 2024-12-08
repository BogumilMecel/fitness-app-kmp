package di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.TrainingScreenModel

val trainingModule = module {
    viewModel { TrainingScreenModel() }
}