package guillermo.lagos.testfgm.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DtoStoresResponse(
    @SerialName("data")
    val `data`: List<DtoData>,
    @SerialName("links")
    val links: DtoLinks,
    @SerialName("meta")
    val meta: DtoMeta
)