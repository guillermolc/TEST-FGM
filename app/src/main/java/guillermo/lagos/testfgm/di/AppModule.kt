package guillermo.lagos.testfgm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.testfgm.R
import guillermo.lagos.testfgm.data.remote.RemoteDataSourceImpl
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun remoteDataSourceProvider(
        @ApplicationContext context: Context,
        client: HttpClient
    ): RemoteDataSource = RemoteDataSourceImpl(
        client = client,
        defaultUrl = context.getString(R.string.url),
        token = context.getString(R.string.token),
        company = context.getString(R.string.company)
    )
}