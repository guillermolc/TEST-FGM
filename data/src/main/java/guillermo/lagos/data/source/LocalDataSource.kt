package guillermo.lagos.data.source

import guillermo.lagos.domain.Page
import kotlinx.coroutines.flow.Flow
interface LocalDataSource {
    suspend fun insertPageWithStoresAndLinks(
        page: Page
    )
    fun getPageWithStoresAndLinks(
        pageId: Int
    ): Flow<Page>
    suspend fun clearData()
}