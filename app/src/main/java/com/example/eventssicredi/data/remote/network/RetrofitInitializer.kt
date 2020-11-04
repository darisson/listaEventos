package com.example.eventssicredi.data.remote.network

import com.example.eventssicredi.data.remote.network.service.EventService
import com.example.eventssicredi.data.remote.network.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val retrofit = Retrofit.Builder()
//        .baseUrl("https://jsonplaceholder.typicode.com/")
        .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun eventService(): EventService = retrofit.create(EventService::class.java)

    fun postService(): PostService = retrofit.create(PostService::class.java)

}