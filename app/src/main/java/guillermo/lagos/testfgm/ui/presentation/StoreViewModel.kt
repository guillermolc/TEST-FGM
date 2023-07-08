package guillermo.lagos.testfgm.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import guillermo.lagos.usecases.FetchStoresUseCase
import javax.inject.Inject

@HiltViewModel
class StoreViewModel
@Inject
constructor(
    private val fetchStoresUseCase: FetchStoresUseCase
):ViewModel(){
    val flow = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 2,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { StorePagingSource(fetchStoresUseCase) }
    ).flow.cachedIn(viewModelScope)
}
