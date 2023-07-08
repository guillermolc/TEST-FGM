package guillermo.lagos.testfgm.data.local

import guillermo.lagos.domain.Links
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Store
import guillermo.lagos.testfgm.data.local.model.LinksEntity
import guillermo.lagos.testfgm.data.local.model.PageEntity
import guillermo.lagos.testfgm.data.local.model.PageWithStoresAndLinks
import guillermo.lagos.testfgm.data.local.model.StoreEntity

fun Page.toEntity(): PageWithStoresAndLinks {
    return PageWithStoresAndLinks(
        page = PageEntity(id = this.number),
        stores = this.list.map { it.toEntity(this.number) },
        link = this.links.toEntity(this.number)
    )
}

fun Store.toEntity(
    pageId: Int
): StoreEntity = StoreEntity(
    id = this.id,
    name = this.name,
    code = this.code,
    address = this.address,
    pageId = pageId
)

fun Links.toEntity(
    pageId: Int
): LinksEntity = LinksEntity(
    id = 0,
    first = this.first,
    last = this.last,
    prev = this.prev,
    next = this.next,
    current = this.current,
    pageId = pageId
)

fun PageWithStoresAndLinks.toDomain(): Page {
    return Page(
        number = this.page.id,
        list = this.stores.map { it.toDomain() },
        links = this.link.toDomain()
    )
}

fun StoreEntity.toDomain(): Store {
    return Store(
        id = this.id,
        name = this.name,
        code = this.code,
        address = this.address
    )
}

fun LinksEntity.toDomain(): Links {
    return Links(
        first = this.first,
        last = this.last,
        prev = this.prev,
        next = this.next,
        current = this.current
    )
}
