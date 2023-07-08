package guillermo.lagos.testfgm.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class DtoPagination(
    @SerialName("current_page")
    val currentPage: Int
)