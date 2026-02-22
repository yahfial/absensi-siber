package com.example.absensitaruna.data.remote

import com.example.absensitaruna.model.AbsensiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // Memanggil endpoint http://10.0.2.2:8000/api/absensi
    @GET("absensi")
    fun getAbsensi(): Call<AbsensiResponse>
}