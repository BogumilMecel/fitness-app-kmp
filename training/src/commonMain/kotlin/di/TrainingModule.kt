package di

import org.koin.dsl.module
import presentation.TrainingScreenModel

val trainingModule = module {
    factory { TrainingScreenModel() }
}