package guillermo.lagos.testfgm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pages")
data class PageEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int
)