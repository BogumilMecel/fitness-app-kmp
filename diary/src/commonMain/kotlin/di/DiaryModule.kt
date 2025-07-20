package di

import api.DiaryApi
import domain.use_case.CreateAvailableDiaryDatesUseCase
import domain.use_case.GetOfflineDiaryEntriesUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.DiaryScreenModel
import presentation.new_product.NewProductScreenModel
import presentation.product.ProductScreenModel
import presentation.search.DiarySearchScreenModel
import repository.DiaryRepository
import repository.DiaryRepositoryImp

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
    viewModel { parameters ->
        DiarySearchScreenModel(
            date = parameters.get(),
            mealName = parameters.get(),
            diaryRepository = get(),
        )
    }
    viewModel {
        NewProductScreenModel(
            diaryRepository = get()
        )
    }
    viewModel { parameters ->
        ProductScreenModel(
            productId = parameters.get(),
            mealName = parameters.get(),
            date = parameters.get(),
        )
    }
}