package com.app.ticketmaster.di

import com.app.ticketmaster.network.BaseService
import com.app.ticketmaster.network.TicketmasterInterface
import com.app.ticketmaster.repository.TicketmasterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicketmasterModule {

    private const val API_KEY = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"

    @Singleton
    @Provides
    fun provideTicketmasterRepository(apiService: TicketmasterInterface): TicketmasterRepository {
        return TicketmasterRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideTicketmasterService(): TicketmasterInterface {
        return Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com/discovery/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseService.provideOkHttpClient())
            .build()
            .create(TicketmasterInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideApiKey(): String {
        return API_KEY
    }
}
