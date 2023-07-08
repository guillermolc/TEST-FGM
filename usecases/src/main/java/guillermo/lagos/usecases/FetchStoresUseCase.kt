package guillermo.lagos.usecases

import guillermo.lagos.data.repository.Repository
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource

class FetchStoresUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        nextPage: String?
    ): Resource<Page> = repository.fetchAndSaveStores(nextPage)
}
