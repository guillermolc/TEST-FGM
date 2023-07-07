package guillermo.lagos.testfgm.data.remote

import android.util.Log
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.domain.Store
import guillermo.lagos.domain.Stores
import guillermo.lagos.testfgm.data.remote.dto.DtoStoresResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient,
    private val defaultUrl: String?,
    private val token: String?,
    private val company: String?
): RemoteDataSource {
    override suspend fun fetchStores(
        nextPage: String?
    ): Stores {
        val url = nextPage ?: defaultUrl
        Log.e(this.javaClass.name, "URL: $url")
        val response: HttpResponse = client.get(url ?: "") {
            headers {
                append(HttpHeaders.Authorization, (token ?: ""))
                append("X-Company-Uuid", (company ?: ""))
            }
            contentType(ContentType.Application.Json)
            parameter("per_page", 10)
        }
        return response
            .receive<DtoStoresResponse>()
            .toStores()
            .also {
                Log.e(this.javaClass.name, "LIST: ${it.list.size}")
            }
    }
}