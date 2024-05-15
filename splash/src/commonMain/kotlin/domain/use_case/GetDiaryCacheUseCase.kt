package domain.use_case

import domain.repository.LoadingRepository

class GetDiaryCacheUseCase(
    private val loadingRepository: LoadingRepository
) {
    suspend operator fun invoke() {
        // TODO: implement use case
    }
}