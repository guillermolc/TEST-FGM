package guillermo.lagos.testfgm.data.remote

import guillermo.lagos.domain.Store
import guillermo.lagos.domain.Stores
import guillermo.lagos.testfgm.data.remote.dto.DtoStoresResponse

fun DtoStoresResponse.toStores() = Stores(
    list = data.map {
        Store(
            name = it.attributes.name,
            code = it.attributes.code,
            address = it.attributes.fullAddress
        )
    },
    nextPage = links.next
)