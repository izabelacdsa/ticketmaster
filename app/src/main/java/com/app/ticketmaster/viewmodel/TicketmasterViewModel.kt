package com.app.ticketmaster.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ticketmaster.model.Event
import com.app.ticketmaster.network.Resource
import com.app.ticketmaster.repository.TicketmasterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketmasterViewModel @Inject constructor(
    private val repository: TicketmasterRepository,
    private val apiKey: String,
) : ViewModel() {

    private val _events = MutableLiveData<Resource<List<Event>>>()
    val events: LiveData<Resource<List<Event>>> = _events

    private var lastSearchText: String = ""

    init {
        getEvents()
    }

    fun getEvents() {
        viewModelScope.launch {
            _events.value = Resource.Loading()
            _events.value = repository.getAllEvents(apiKey)
        }
    }

    fun getEventsByCity(city: String) {
        viewModelScope.launch {
            if (city.isBlank()) {
                getEvents()
            } else {
                lastSearchText = city
                _events.value = Resource.Loading()
                _events.value = repository.getEventsByCity(apiKey, city)
            }
        }
    }
}
