package guillermo.lagos.testfgm.data.local

import guillermo.lagos.data.source.LocalDataSource
import guillermo.lagos.domain.Links
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    db: StoreDatabase
): LocalDataSource {
    private val storeDao = db.storeDao()

    override suspend fun insertPageWithStoresAndLinks(
        page: Page
    ) {
        val entity = page.toEntity()
        storeDao.insertPageWithStoresAndLinks(entity)
    }
    override fun getPageWithStoresAndLinks(
        pageId: Int
    ): Flow<Page> = storeDao.getPageWithStoresAndLinks(
        pageId
    ).map {
        it.toDomain()
    }

    override suspend fun clearData() = storeDao.clearData()
}