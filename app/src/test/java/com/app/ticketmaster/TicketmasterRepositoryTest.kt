package com.app.ticketmaster

import com.app.ticketmaster.model.TicketmasterResponse
import com.app.ticketmaster.network.Resource
import com.app.ticketmaster.network.TicketmasterInterface
import com.app.ticketmaster.repository.TicketmasterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class TicketmasterRepositoryTest {

    private lateinit var repository: TicketmasterRepository
    private lateinit var apiService: TicketmasterInterface

    @Before
    fun setUp() {
        apiService = mock(TicketmasterInterface::class.java)
        repository = TicketmasterRepository(apiService)
    }

    @Test
    fun `getAllEvents should return success resource`() = runBlocking {
        // Given
        val apiKey = "fakeApiKey"
        val ticketmasterResponse = TicketmasterResponse(
            TestData.embedded,
            TestData.links,
            TestData.page,
        )

        `when`(apiService.getAllEvents(apiKey)).thenReturn(ticketmasterResponse)

        // When
        val result = repository.getAllEvents(apiKey)

        // Then
        assertTrue(result is Resource.Success)
    }

    @Test
    fun `getAllEvents should return error resource when IOException occurs`() = runBlocking {
        // Given
        val apiKey = "fakeApiKey"
        `when`(apiService.getAllEvents(apiKey)).thenAnswer { throw RuntimeException("Network error") }

        // When
        val result = repository.getAllEvents(apiKey)

        // Then
        assertTrue(result is Resource.Error)
    }
}
