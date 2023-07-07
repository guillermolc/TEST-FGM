package guillermo.lagos.domain

data class Resource<out T>(
    val data: T? = null,
    val exception: Throwable? = null
)