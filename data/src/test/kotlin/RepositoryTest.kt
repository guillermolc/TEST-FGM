package guillermo.lagos.data

import guillermo.lagos.data.repository.Repository
import guillermo.lagos.data.source.LocalDataSource
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.domain.Links
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class RepositoryTest {

    private val mockRemoteDataSource = mockk<RemoteDataSource>()
    private val mockLocalDataSource = mockk<LocalDataSource>()
    private val repository = Repository(mockRemoteDataSource, mockLocalDataSource)

    @Test
    fun `test fetch && save stores with success`() = runTest {
        val expectedPage = Page(
            number = 1,
            list = listOf(
                Store(
                    id = "1",
                    name = "Store1",
                    code = "S1",
                    address = "Address1"
                )
            ),
            links = Links(
                first = "first",
                last = "last",
                current = "current"
            )
        )
        val expectedResponse = Resource.Success(expectedPage)
        coEvery { mockRemoteDataSource.fetchStores(any()) } returns expectedResponse
        coEvery { mockLocalDataSource.insertPageWithStoresAndLinks(any()) } returns Unit
        coEvery { mockLocalDataSource.getPageWithStoresAndLinks(any()) } returns flowOf(expectedPage)
        val actualResource = repository.fetchAndSaveStores(null)
        Assert.assertEquals(expectedResponse, actualResource)
        coVerify { mockRemoteDataSource.fetchStores(null) }
        coVerify { mockLocalDataSource.insertPageWithStoresAndLinks(expectedPage) }
        coVerify { mockLocalDataSource.getPageWithStoresAndLinks(expectedPage.number) }
    }

    @Test
    fun `test fetch && save stores with error`() = runTest {
        val expectedException = RuntimeException("Error")
        val expectedResponse = Resource.Error(expectedException)
        coEvery { mockRemoteDataSource.fetchStores(any()) } returns expectedResponse
        val actualResource = repository.fetchAndSaveStores(null)
        Assert.assertEquals(expectedResponse, actualResource)
        coVerify { mockRemoteDataSource.fetchStores(null) }
        coVerify(exactly = 0) { mockLocalDataSource.insertPageWithStoresAndLinks(any()) }
        coVerify(exactly = 0) { mockLocalDataSource.getPageWithStoresAndLinks(any()) }
    }
}



