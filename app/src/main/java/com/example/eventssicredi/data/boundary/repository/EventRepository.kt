package com.example.eventssicredi.data.boundary.repository

import androidx.lifecycle.LiveData
import com.example.eventssicredi.data.local.database.EventDao
import com.example.eventssicredi.domain.models.Event

class EventRepository (
    private val eventDao: EventDao
) {
    val allEvent: LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun insert(event: List<Event>) {
        eventDao.insert(event)
    }

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    suspend fun update(event: Event) {
        eventDao.insert(event)
    }
}