package guillermo.lagos.usecases

import guillermo.lagos.data.repository.Repository
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import guillermo.lagos.domain.Stores
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FetchStoresUseCaseTest {

    private lateinit var repository: Repository
    private lateinit var useCase: FetchStoresUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = FetchStoresUseCase(repository)
    }

    @Test
    fun `invoke returns success resource when repository fetches stores successfully`() = runBlocking {
        val nextPage = "test_page"
        val expectedStore = Store(
            name = "Test",
            code = "1",
            address = "Test Address"
        )
        val expectedStores = Stores(listOf(expectedStore), nextPage)
        val expectedResource = Resource(data = expectedStores)
        coEvery { repository.fetchStores(nextPage) } returns expectedResource
        val result = useCase.invoke(nextPage)
        assertEquals(expectedResource, result)
    }

    @Test
    fun `invoke returns error resource when repository fetch fails`() = runBlocking {
        val nextPage = "test_page"
        val expectedException = Exception("Fetch failed")
        val expectedResource = Resource<Stores>(exception = expectedException)
        coEvery { repository.fetchStores(nextPage) } returns expectedResource
        val result = useCase.invoke(nextPage)
        assertEquals(expectedResource, result)
    }
}
