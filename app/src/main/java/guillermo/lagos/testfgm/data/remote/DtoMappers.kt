package guillermo.lagos.testfgm.data.remote

import guillermo.lagos.domain.Links
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Store
import guillermo.lagos.testfgm.data.remote.dto.DtoStoresResponse

fun DtoStoresResponse.toPage() = Page(
    number = meta.pagination.currentPage,
    list = data.map {
        Store(
            id = it.id,
            name = it.attributes.name,
            code = it.attributes.code,
            address = it.attributes.fullAddress
        )
    },
    links = Links(
        first = links.first,
        last = links.last,
        prev = links.prev,
        next = links.next,
        current = links.self
    )
)