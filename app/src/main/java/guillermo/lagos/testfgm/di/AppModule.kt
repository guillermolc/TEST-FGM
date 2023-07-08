package guillermo.lagos.testfgm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import guillermo.lagos.data.source.LocalDataSource
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.testfgm.R
import guillermo.lagos.testfgm.data.local.LocalDataSourceImpl
import guillermo.lagos.testfgm.data.local.StoreDatabase
import guillermo.lagos.testfgm.data.remote.RemoteDataSourceImpl
import io.ktor.client.HttpClient
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        StoreDatabase::class.java,
        "store_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun localDataSourceProvider(
        db: StoreDatabase
    ): LocalDataSource = LocalDataSourceImpl(db)
}