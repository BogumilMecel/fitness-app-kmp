package di

import DiaryApi
import DiaryRepositoryImp
import domain.repository.DiaryRepository
import domain.use_case.CreateAvailableDiaryDatesUseCase
import domain.use_case.GetOfflineDiaryEntriesUseCase
import org.koin.dsl.module
import presentation.search.DiarySearchScreenModel
import presentation.DiaryScreenModel

val diaryModule = module {
    single<DiaryRepository> {
        DiaryRepositoryImp(
            diaryApi = DiaryApi(httpClient = get()),
            diaryDao = get()
        )
    }
    factory {
        DiaryScreenModel(
            createAvailableDiaryDatesUseCase = CreateAvailableDiaryDatesUseCase(settingsService = get()),
            getOfflineDiaryEntriesUseCase = GetOfflineDiaryEntriesUseCase(diaryRepository = get())
        )
    }
    factory {
        DiarySearchScreenModel()
    }
}