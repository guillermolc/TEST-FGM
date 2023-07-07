package guillermo.lagos.data.repository

import guillermo.lagos.data.source.RemoteDataSource

class Repository (
    private val remoteDataSource: RemoteDataSource
){
    suspend fun fetchStores(
        nextPage: String?
    ) = remoteDataSource.fetchStores(
        nextPage = nextPage
    )
}