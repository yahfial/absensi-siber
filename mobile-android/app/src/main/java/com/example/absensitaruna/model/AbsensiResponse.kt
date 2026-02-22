package com.example.absensitaruna.model

// Menangkap respons utama
data class AbsensiResponse(
    val status: String,
    val data: List<AbsensiItem>
)

// Menangkap isi array 'data'
data class AbsensiItem(
    val id: Int,
    val jadwal_id: Int,
    val user_id: Int,
    val waktu_absen: String,
    val status: String
)