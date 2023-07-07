package guillermo.lagos.testfgm.ui.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import guillermo.lagos.usecases.FetchStoresUseCase

class StorePagingSource(
    private val fetchStoresUseCase: FetchStoresUseCase,
    private var nextPageUrl: String? = null
) : PagingSource<Int, Store>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Store> {
        val page = params.key ?: 1
        return try {
            when (
                val response = fetchStoresUseCase.invoke(nextPageUrl)
            ) {
                is Resource.Success -> {
                    nextPageUrl = response.data.nextPage
                    LoadResult.Page(
                        data = response.data.list,
                        prevKey = null,
                        nextKey = if (response.data.nextPage != null) page + 1
                        else null
                    )
                }
                is Resource.Error -> LoadResult.Error(response.exception)
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Store>
    ): Int? = null
}