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
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val client: HttpClient,
    private val defaultUrl: String?,
    private val token: String?,
    private val company: String?
) : RemoteDataSource {
    override suspend fun fetchStores(
        nextPage: String?
    ): Resource<Page> = try {
        if (
            !defaultUrl.isNullOrEmpty() &&
            !token.isNullOrEmpty() &&
            !company.isNullOrEmpty()
        ) {
            val url = nextPage ?: defaultUrl
            val response: HttpResponse = client.get(url) {
                headers {
                    append(HttpHeaders.Authorization, token)
                    append("X-Company-Uuid", company)
                }
                contentType(ContentType.Application.Json)
                parameter("per_page", 10)
            }
            if (response.status == HttpStatusCode.OK) {
                val dtoResponse = response.receive<DtoStoresResponse>()
                val page = dtoResponse.toPage()
                Log.e(this.javaClass.name, "URL: $url")
                Resource.Success(data = page)
            } else {
                val errorMessage = getErrorMessage(response.status)
                Resource.Error(exception = Exception(errorMessage))
            }
        } else {
            Resource.Error(exception = Exception("Ups! Network parameters are missing"))
        }
    } catch (e: ResponseException) {
        Resource.Error(exception = e)
    } catch (e: UnknownHostException) {
        Resource.Error(exception = Exception("Ups! Poor signal connection"))
    }

    private fun getErrorMessage(
        status: HttpStatusCode
    ) = when (status) {
        HttpStatusCode.InternalServerError -> "Ups! Internal Server Error (500)"
        HttpStatusCode.Forbidden -> "Ups! Forbidden Access (403)"
        HttpStatusCode.NotFound -> "Ups! Resource Not Found (404)"
        else -> "Ups! HTTP Error: ${status.value}"
    }
}
