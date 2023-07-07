package guillermo.lagos.testfgm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import guillermo.lagos.data.repository.Repository
import guillermo.lagos.usecases.FetchStoresUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    @ViewModelScoped
    fun fetchStoresUseCaseProvider(
        repository: Repository
    ) = FetchStoresUseCase(
        repository = repository
    )
}