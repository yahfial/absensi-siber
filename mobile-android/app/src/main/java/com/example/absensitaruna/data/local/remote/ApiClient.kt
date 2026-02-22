package com.example.absensitaruna.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    // 10.0.2.2 adalah IP khusus emulator Android untuk mengakses localhost laptop
    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}