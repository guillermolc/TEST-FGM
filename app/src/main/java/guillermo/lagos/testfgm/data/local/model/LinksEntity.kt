package guillermo.lagos.testfgm.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "links")
data class LinksEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first") val first: String,
    @ColumnInfo(name = "last") val last: String,
    @ColumnInfo(name = "prev") val prev: String? = null,
    @ColumnInfo(name = "next") val next: String? = null,
    @ColumnInfo(name = "current") val current: String,
    @ColumnInfo(name = "page_id") val pageId: Int
)