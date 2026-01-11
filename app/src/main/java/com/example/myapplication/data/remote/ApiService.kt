package com.example.myapplication.data.remote

import com.example.myapplication.data.model.ItemResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("/items")
    suspend fun getListItems(): List<ItemResponse>

    @Multipart
    @POST("/upload-and-get-image")
    suspend fun uploadData(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): ResponseBody
}