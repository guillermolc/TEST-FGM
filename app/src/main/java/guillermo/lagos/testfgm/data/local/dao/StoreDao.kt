package guillermo.lagos.testfgm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import guillermo.lagos.testfgm.data.local.model.LinksEntity
import guillermo.lagos.testfgm.data.local.model.PageEntity
import guillermo.lagos.testfgm.data.local.model.PageWithStoresAndLinks
import guillermo.lagos.testfgm.data.local.model.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Transaction
    suspend fun insertPageWithStoresAndLinks(page: PageWithStoresAndLinks) {
        clearData()
        insertPage(page.page)
        insertStores(page.stores)
        insertLinks(page.link)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPage(page: PageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStores(stores: List<StoreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLinks(link: LinksEntity)

    @Query("SELECT * FROM pages WHERE id = :pageId")
    fun getPageWithStoresAndLinks(pageId: Int): Flow<PageWithStoresAndLinks>

    @Query("DELETE FROM pages")
    suspend fun clearPages()

    @Query("DELETE FROM stores")
    suspend fun clearStores()

    @Query("DELETE FROM links")
    suspend fun clearLinks()

    @Transaction
    suspend fun clearData() {
        clearPages()
        clearStores()
        clearLinks()
    }
}

