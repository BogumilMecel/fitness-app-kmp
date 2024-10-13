package di

import data.api.MainApiClient
import data.repository.MainRepositoryImp
import main_screen.presentation.TabNavigatorModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        TabNavigatorModel(
            mainRepository = MainRepositoryImp(
                mainApiClient = MainApiClient(httpClient = get())
            )
        )
    }
}