package com.example.absensitaruna

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.absensitaruna.data.remote.ApiClient
import com.example.absensitaruna.data.remote.ApiService
import com.example.absensitaruna.model.AbsensiResponse
import com.example.absensitaruna.ui.adapter.AbsensiAdapter
import com.example.absensitaruna.data.local.DatabaseHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvAbsensi: RecyclerView
    private lateinit var adapter: AbsensiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAbsensi = findViewById(R.id.rvAbsensi)
        rvAbsensi.layoutManager = LinearLayoutManager(this)

        adapter = AbsensiAdapter(emptyList())
        rvAbsensi.adapter = adapter

        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val apiService = ApiClient.instance.create(ApiService::class.java)

        apiService.getAbsensi().enqueue(object : Callback<AbsensiResponse> {
            override fun onResponse(call: Call<AbsensiResponse>, response: Response<AbsensiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val dataAbsensi = response.body()!!.data

                    // 1. Tampilkan ke layar (RecyclerView)
                    adapter.updateData(dataAbsensi)

                    // 2. LOGIKA BARU: Simpan ke SQLite untuk cadangan Offline
                    val dbHelper = DatabaseHelper(this@MainActivity)
                    for (item in dataAbsensi) {
                        dbHelper.insertCache(
                            item.id,
                            "Taruna ID: ${item.user_id}", // Simpan info user
                            "NPT: 2423102087",          // Data dummy NPT sesuai NIM-mu [cite: 3]
                            item.waktu_absen,
                            item.status
                        )
                    }

                    Toast.makeText(this@MainActivity, "Data Sinkron & Disimpan Lokal", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AbsensiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi Error. Memuat data Offline...", Toast.LENGTH_LONG).show()

                // Ambil data dari SQLite jika API gagal diakses
                val dbHelper = DatabaseHelper(this@MainActivity)
                val offlineData = dbHelper.getAllCache()

                if (offlineData.isNotEmpty()) {
                    adapter.updateData(offlineData)
                } else {
                    Toast.makeText(this@MainActivity, "Tidak ada data offline tersimpan", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}