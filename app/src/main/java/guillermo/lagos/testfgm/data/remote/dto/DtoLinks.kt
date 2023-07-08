package guillermo.lagos.testfgm.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DtoLinks(
    @SerialName("first")
    val first: String,
    @SerialName("last")
    val last: String,
    @SerialName("next")
    val next: String? = null,
    @SerialName("prev")
    val prev: String? = null,
    @SerialName("self")
    val self: String
)