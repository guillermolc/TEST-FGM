package guillermo.lagos.testfgm.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class PageWithStoresAndLinks (
    @Embedded val page: PageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "page_id"
    )
    val stores: List<StoreEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "page_id"
    )
    val link: LinksEntity
)