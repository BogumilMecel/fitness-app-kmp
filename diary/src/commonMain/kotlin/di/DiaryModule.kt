package di

import domain.use_case.CreateAvailableDiaryDatesUseCase
import org.koin.dsl.module
import presentation.DiaryScreenModel

val diaryModule = module {
    factory {
        DiaryScreenModel(
            createAvailableDiaryDatesUseCase = CreateAvailableDiaryDatesUseCase(settingsService = get())
        )
    }
}