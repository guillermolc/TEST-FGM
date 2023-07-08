package guillermo.lagos.data.repository

import guillermo.lagos.data.source.LocalDataSource
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource
import kotlinx.coroutines.flow.first

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun fetchAndSaveStores(
        nextPage: String?
    ): Resource<Page> {
        return when (val response = remoteDataSource.fetchStores(nextPage)) {
            is Resource.Success -> {
                localDataSource.insertPageWithStoresAndLinks(response.data)
                localDataSource.getPageWithStoresAndLinks(response.data.number)
                    .first().let {
                        Resource.Success(it)
                    }
            }
            is Resource.Error -> response
        }
    }
}

