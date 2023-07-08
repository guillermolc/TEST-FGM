package guillermo.lagos.testfgm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import guillermo.lagos.testfgm.data.local.dao.StoreDao
import guillermo.lagos.testfgm.data.local.model.LinksEntity
import guillermo.lagos.testfgm.data.local.model.PageEntity
import guillermo.lagos.testfgm.data.local.model.StoreEntity

@Database(
    entities = [
        StoreEntity::class,
        LinksEntity::class,
        PageEntity::class
    ], version = 1,
    exportSchema = false
)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}
