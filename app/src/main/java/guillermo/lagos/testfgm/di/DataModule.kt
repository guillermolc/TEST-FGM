package guillermo.lagos.testfgm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import guillermo.lagos.data.repository.Repository
import guillermo.lagos.data.source.LocalDataSource
import guillermo.lagos.data.source.RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun repository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) = Repository(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )
}