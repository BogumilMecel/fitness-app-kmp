package di

import domain.use_case.GetProductDiaryAndSaveOfflineUseCase
import domain.use_case.GetProductsAndSaveOfflineUseCase
import domain.use_case.GetRecipeDiaryAndSaveOfflineUseCase
import domain.use_case.GetRecipesAndSaveOfflineUseCase
import domain.use_case.InitialDiaryDataUseCases
import main_screen.presentation.TabNavigatorModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        TabNavigatorModel(
            initialDiaryDataUseCases = InitialDiaryDataUseCases(
                getProductsAndSaveOfflineUseCase = GetProductsAndSaveOfflineUseCase(
                    diaryRepository = get(),
                    settingsService = get()
                ),
                getProductDiaryAndSaveOfflineUseCase = GetProductDiaryAndSaveOfflineUseCase(
                    diaryRepository = get(),
                ),
                getRecipesAndSaveOfflineUseCase = GetRecipesAndSaveOfflineUseCase(
                    diaryRepository = get(),
                    settingsService = get(),
                ),
                getRecipeDiaryAndSaveOfflineUseCase = GetRecipeDiaryAndSaveOfflineUseCase(
                    diaryRepository = get(),
                )
            )
        )
    }
}