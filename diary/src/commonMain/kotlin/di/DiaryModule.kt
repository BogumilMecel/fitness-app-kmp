package di

import DiaryApi
import DiaryRepositoryImp
import domain.repository.DiaryRepository
import domain.use_case.CreateAvailableDiaryDatesUseCase
import domain.use_case.GetOfflineDiaryEntriesUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.DiaryScreenModel
import presentation.search.DiarySearchScreenModel

val diaryModule = module {
    single<DiaryRepository> {
        DiaryRepositoryImp(
            diaryApi = DiaryApi(httpClient = get()),
            diaryDao = get()
        )
    }
    viewModel {
        DiaryScreenModel(
            createAvailableDiaryDatesUseCase = CreateAvailableDiaryDatesUseCase(settingsService = get()),
            getOfflineDiaryEntriesUseCase = GetOfflineDiaryEntriesUseCase(diaryRepository = get())
        )
    }
    viewModel {
        DiarySearchScreenModel(
            diaryRepository = get(),
            resourcesService = get(),
        )
    }
}