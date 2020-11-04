package com.example.eventssicredi.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.eventssicredi.data.local.database.EventDataBase
import com.example.eventssicredi.domain.models.Event
import com.example.eventssicredi.data.remote.network.RetrofitInitializer
import com.example.eventssicredi.data.remote.network.service.EventService
import com.example.eventssicredi.data.boundary.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: EventRepository
    val allEvent: LiveData<List<Event>>
    private val eventService: EventService

    init {
        val eventDao = EventDataBase.getDatabase(application, viewModelScope).eventDao()
        eventService = RetrofitInitializer().eventService()
        repository = EventRepository(eventDao)
        allEvent = repository.allEvent
        loadEvent()
    }

    fun loadEvent() = viewModelScope.launch {
        insert(eventService.listEvents())
    }

    fun insert(event: List<Event>) = viewModelScope.launch {
        repository.insert(event)
    }

    fun update(event: Event) = viewModelScope.launch {
        repository.update(event)
    }

}