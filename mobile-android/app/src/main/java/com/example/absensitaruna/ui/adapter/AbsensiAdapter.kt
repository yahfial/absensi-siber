package com.example.absensitaruna.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.absensitaruna.R
import com.example.absensitaruna.model.AbsensiItem

class AbsensiAdapter(private var listAbsensi: List<AbsensiItem>) :
    RecyclerView.Adapter<AbsensiAdapter.ViewHolder>() {

    // Menghubungkan variabel dengan ID di layout item_absensi.xml
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaTaruna)
        val tvNpt: TextView = view.findViewById(R.id.tvNptTaruna)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    // Mengembangkan (inflate) desain kartu XML menjadi tampilan nyata
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_absensi, parent, false)
        return ViewHolder(view)
    }

    // Memasukkan data dari Kotlin (JSON) ke dalam teks UI
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listAbsensi[position]

        // Catatan: Karena data API kita murni dari tabel absensi,
        // kita tampilkan User ID dan Waktu Absen sementara
        holder.tvNama.text = "User ID: ${item.user_id}"
        holder.tvNpt.text = "Waktu: ${item.waktu_absen}"
        holder.tvStatus.text = item.status

        // Manipulasi warna status (Hijau jika Hadir, Merah jika Alpha)
        if (item.status.equals("Hadir", ignoreCase = true)) {
            holder.tvStatus.setTextColor(Color.parseColor("#10B981")) // Hijau
        } else {
            holder.tvStatus.setTextColor(Color.parseColor("#EF4444")) // Merah
        }
    }

    override fun getItemCount(): Int {
        return listAbsensi.size
    }

    // Fungsi untuk memperbarui data saat ada tarikan API baru
    fun updateData(newList: List<AbsensiItem>) {
        listAbsensi = newList
        notifyDataSetChanged()
    }
}