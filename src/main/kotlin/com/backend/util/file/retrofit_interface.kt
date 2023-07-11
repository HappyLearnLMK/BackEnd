package com.backend.util.file

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface retrofit_interface{
    @Multipart
    @POST("product/reviewImage")
    fun post_image_Request(
        @Part("memberId") memberId: String,
        @Part imageFile : MultipartBody.Part): Call<String>
}
