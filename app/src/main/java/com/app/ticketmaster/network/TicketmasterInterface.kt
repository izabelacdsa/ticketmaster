package com.app.ticketmaster.network

import com.app.ticketmaster.model.TicketmasterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketmasterInterface {

    @GET("events.json")
    suspend fun getEventsByCity(
        @Query("apikey") apiKey: String,
        @Query("city") city: String,
        @Query("source") source: String,
    ): TicketmasterResponse

    @GET("events.json")
    suspend fun getAllEvents(
        @Query("apikey") apiKey: String,
    ): TicketmasterResponse
}
