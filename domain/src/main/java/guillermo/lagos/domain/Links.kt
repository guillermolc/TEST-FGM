package guillermo.lagos.domain

data class Links (
    val first: String,
    val last: String,
    val prev: String? = null,
    val next: String? = null,
    val current: String
)