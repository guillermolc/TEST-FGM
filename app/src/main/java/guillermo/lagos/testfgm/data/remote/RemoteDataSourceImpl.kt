package guillermo.lagos.testfgm.data.remote

import android.util.Log
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource
import guillermo.lagos.testfgm.data.remote.dto.DtoStoresResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
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
    ): Resource<Page> = try {
        if (
            !defaultUrl.isNullOrEmpty()
            &&
            !token.isNullOrEmpty()
            &&
            !company.isNullOrEmpty()
        ){
            val url = nextPage ?: defaultUrl
            val response: HttpResponse = client.get(url) {
                headers {
                    append(HttpHeaders.Authorization, token)
                    append("X-Company-Uuid", company)
                }
                contentType(ContentType.Application.Json)
                parameter("per_page", 10)
            }
            if (response.status == HttpStatusCode.OK) response.receive<DtoStoresResponse>()
                .toPage()
                .let {
                    Log.e(this.javaClass.name, "URL: $url")
                    Resource.Success(data = it)
                }
            else Resource.Error(exception = Exception("Server responded with status ${response.status.value}"))
        } else Resource.Error(exception = Exception("Network parameters are missing"))
    } catch (e: ResponseException) {
        Resource.Error(exception = e)
    }
}