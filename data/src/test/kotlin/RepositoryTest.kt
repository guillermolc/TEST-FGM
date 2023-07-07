package guillermo.lagos.data

import guillermo.lagos.data.repository.Repository
import guillermo.lagos.data.source.RemoteDataSource
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import guillermo.lagos.domain.Stores
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        repository = Repository(remoteDataSource)
    }

    @Test
    fun `fetchStores returns success resource when remote data source fetches stores successfully`() = runBlocking {
        val nextPage = "test_page"
        val expectedStore = Store(name = "Test", code = "1", address = "Test Address")
        val expectedStores = Stores(listOf(expectedStore), nextPage)
        val expectedResource = Resource(data = expectedStores)
        coEvery { remoteDataSource.fetchStores(nextPage) } returns expectedResource
        val result = repository.fetchStores(nextPage)
        assertEquals(expectedResource, result)
    }

    @Test
    fun `fetchStores returns error resource when remote data source fetch fails`() = runBlocking {
        val nextPage = "test_page"
        val expectedException = Exception("Fetch failed")
        val expectedResource = Resource<Stores>(exception = expectedException)
        coEvery { remoteDataSource.fetchStores(nextPage) } returns expectedResource
        val result = repository.fetchStores(nextPage)
        assertEquals(expectedResource, result)
    }
}
