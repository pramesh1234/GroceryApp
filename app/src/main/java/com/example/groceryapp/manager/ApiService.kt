package com.example.groceryapp.manager

import com.example.groceryapp.model.GroceryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
    suspend fun getGroceries(@Query("api-key") apiKey:String,@Query("format") format:String,@Query("offset") offset:String): Response<GroceryModel>
}