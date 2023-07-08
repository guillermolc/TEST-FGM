package guillermo.lagos.testfgm.ui.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import guillermo.lagos.usecases.FetchStoresUseCase

class StorePagingSource(
    private val fetchStoresUseCase: FetchStoresUseCase,
    private var nextPageUrl: String? = null
) : PagingSource<String, Store>() {

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, Store> {
        return try {
            when (
                val response = fetchStoresUseCase.invoke(nextPageUrl)
            ) {
                is Resource.Success -> {
                    nextPageUrl = response.data.links.next
                    LoadResult.Page(
                        data = response.data.list,
                        prevKey = null,
                        nextKey = nextPageUrl
                    )
                }
                is Resource.Error -> LoadResult.Error(response.exception)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<String, Store>
    ): String? = nextPageUrl
}

