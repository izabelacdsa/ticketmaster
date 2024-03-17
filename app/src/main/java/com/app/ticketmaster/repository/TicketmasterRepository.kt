package com.app.ticketmaster.repository

import com.app.ticketmaster.model.Event
import com.app.ticketmaster.network.Resource
import com.app.ticketmaster.network.TicketmasterInterface
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketmasterRepository @Inject constructor(private val apiService: TicketmasterInterface) {

    suspend fun getAllEvents(apiKey: String): Resource<List<Event>> {
        return executeWithRetry { apiService.getAllEvents(apiKey)._embedded.events }
    }

    suspend fun getEventsByCity(apiKey: String, city: String): Resource<List<Event>> {
        return executeWithRetry {
            apiService.getEventsByCity(
                apiKey,
                city,
                "ticketmaster",
            )._embedded.events
        }
    }

    private suspend fun <T> executeWithRetry(apiCall: suspend () -> T): Resource<T> {
        var delayMillis = INITIAL_DELAY_MILLIS
        while (true) {
            try {
                val response = apiCall.invoke()
                return Resource.Success(response)
            } catch (error: Exception) {
                if (error is HttpException && error.code() == 429) {
                    delay(delayMillis)
                    delayMillis *= BACKOFF_MULTIPLIER
                } else {
                    return handleException(error)
                }
            }
        }
    }

    private fun <T> handleException(error: Exception): Resource.Error<T> {
        return when (error) {
            is HttpException -> {
                val errorCode = error.code()
                Resource.Error("HTTP Error: $errorCode")
            }

            is IOException -> {
                Resource.Error("Network error: ${error.localizedMessage}")
            }

            else -> {
                Resource.Error("An unknown error occurred: ${error.localizedMessage}")
            }
        }
    }

    companion object {
        private const val INITIAL_DELAY_MILLIS = 1000L
        private const val BACKOFF_MULTIPLIER = 2
    }
}
