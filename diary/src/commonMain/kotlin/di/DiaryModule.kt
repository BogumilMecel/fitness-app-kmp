package di

import DiaryApi
import DiaryRepositoryImp
import domain.repository.DiaryRepository
import domain.use_case.CreateAvailableDiaryDatesUseCase
import domain.use_case.GetOfflineDiaryEntriesUseCase
import org.koin.dsl.module
import presentation.DiaryTabModel

val diaryModule = module {
    single<DiaryRepository> {
        DiaryRepositoryImp(
            diaryApi = DiaryApi(httpClient = get()),
            diaryDao = get()
        )
    }
    factory {
        DiaryTabModel(
            createAvailableDiaryDatesUseCase = CreateAvailableDiaryDatesUseCase(settingsService = get()),
            getOfflineDiaryEntriesUseCase = GetOfflineDiaryEntriesUseCase(diaryRepository = get())
        )
    }
}