package guillermo.lagos.data.source

import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Stores

interface RemoteDataSource {
    suspend fun fetchStores(
        nextPage: String?
    ): Resource<Stores>
}