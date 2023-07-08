package guillermo.lagos.testfgm.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DtoData(
    @SerialName("attributes")
    val attributes: DtoAttributes,
    @SerialName("id")
    val id: String
)