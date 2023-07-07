package guillermo.lagos.data.source

import guillermo.lagos.domain.Stores

interface RemoteDataSource {
    suspend fun fetchStores(
        nextPage: String?
    ): Stores
}