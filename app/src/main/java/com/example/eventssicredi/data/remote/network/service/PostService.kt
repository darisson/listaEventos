package com.example.eventssicredi.data.remote.network.service

import com.example.eventssicredi.domain.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    fun list(): Call<List<Post>>

}