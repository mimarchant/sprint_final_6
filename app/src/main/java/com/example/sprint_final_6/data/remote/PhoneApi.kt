package com.example.sprint_final_6.data.remote

import com.example.sprint_final_6.data.remote.detail.CellPhoneDetail
import com.example.sprint_final_6.data.remote.list.CellPhoneList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {

    //get list of phones
    @GET("products/")
    suspend fun getDataPhones(): Response<List<CellPhoneList>>

    //get phone details
    @GET("details/{id}")
    suspend fun getDataPhoneDetails(@Path("id") id: Int): Response<CellPhoneDetail>
}