package guillermo.lagos.domain

import org.junit.Assert
import org.junit.Test
class ResourceTest {
    @Test
    fun `test success resource`() {
        val expectedData = "Success"
        val actualData = when (
            val successResource: Resource<String> = Resource.Success(expectedData)
        ) {
            is Resource.Success -> successResource.data
            else -> null
        }
        Assert.assertEquals(expectedData, actualData)
    }
    @Test
    fun `test error resource`() {
        val expectedException = RuntimeException("Error")
        val actualException = when (
            val errorResource: Resource<String> = Resource.Error(expectedException)
        ) {
            is Resource.Error -> errorResource.exception
            else -> null
        }
        Assert.assertEquals(expectedException, actualException)
    }
}
