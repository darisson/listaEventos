package com.example.eventssicredi.data.remote.network.service

import com.example.eventssicredi.domain.models.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventService {

    @GET("events")
    fun list(): Call<List<Event>>

    @GET("events")
    suspend fun listEvents(): List<Event>
}




