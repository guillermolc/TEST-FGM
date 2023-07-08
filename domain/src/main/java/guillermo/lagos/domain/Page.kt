package guillermo.lagos.domain

data class Page(
    val number: Int,
    val list: List<Store>,
    val links: Links
)