package di

import data.api.IntroductionApi
import data.repository.IntroductionRepositoryImp
import domain.repository.IntroductionRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.IntroductionScreenModel

val introductionModule = module {
    single { IntroductionApi(httpClient = get()) }
    single<IntroductionRepository> { IntroductionRepositoryImp(introductionApi = get()) }
    viewModel { 
        IntroductionScreenModel(introductionRepository = get())
    }
} 