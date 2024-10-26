package domain.use_case

data class InitialDiaryDataUseCases(
    val getProductDiaryAndSaveOfflineUseCase: GetProductDiaryAndSaveOfflineUseCase,
    val getRecipeDiaryAndSaveOfflineUseCase: GetRecipeDiaryAndSaveOfflineUseCase,
    val getProductsAndSaveOfflineUseCase: GetProductsAndSaveOfflineUseCase,
    val getRecipesAndSaveOfflineUseCase: GetRecipesAndSaveOfflineUseCase,
)