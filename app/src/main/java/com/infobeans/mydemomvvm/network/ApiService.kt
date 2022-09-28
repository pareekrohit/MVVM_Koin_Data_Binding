package com.infobeans.mydemomvvm.network


import com.infobeans.mydemomvvm.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>
}