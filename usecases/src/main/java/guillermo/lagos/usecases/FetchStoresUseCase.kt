package guillermo.lagos.usecases

import guillermo.lagos.data.repository.Repository

class FetchStoresUseCase(
    private val repository: Repository
) {
    suspend fun invoke(
        nextPage: String?
    ) = repository.fetchStores(
        nextPage = nextPage
    )
}