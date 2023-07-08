package guillermo.lagos.testfgm.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DtoAttributes(
    @SerialName("code")
    val code: String,
    @SerialName("full_address")
    val fullAddress: String,
    @SerialName("name")
    val name: String
)