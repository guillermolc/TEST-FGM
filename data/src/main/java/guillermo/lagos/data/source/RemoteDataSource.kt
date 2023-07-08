package guillermo.lagos.data.source

import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource

interface RemoteDataSource {
    suspend fun fetchStores(
        nextPage: String?
    ): Resource<Page>
}