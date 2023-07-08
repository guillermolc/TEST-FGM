package guillermo.lagos.usecases

import guillermo.lagos.data.repository.Repository
import guillermo.lagos.domain.Links
import guillermo.lagos.domain.Page
import guillermo.lagos.domain.Resource
import guillermo.lagos.domain.Store
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FetchStoresUseCaseTest {

    private val mockRepository = mockk<Repository>()
    private val useCase = FetchStoresUseCase(mockRepository)

    @Test
    fun `test fetch && save stores`() {
        val expectedPage = Page(
            number = 1,
            list = listOf(
                Store(
                    id = "1",
                    name = "Store1",
                    code = "S1",
                    address = "Address1",
                )
            ),
            links = Links(
                first = "first",
                last = "last",
                current = "current"
            )
        )
        val expectedResource = Resource.Success(expectedPage)
        coEvery { mockRepository.fetchAndSaveStores(any()) } returns expectedResource
        val actualResource = runBlocking { useCase.invoke(null) }
        Assert.assertEquals(expectedResource, actualResource)
        coVerify { mockRepository.fetchAndSaveStores(null) }
    }
}

