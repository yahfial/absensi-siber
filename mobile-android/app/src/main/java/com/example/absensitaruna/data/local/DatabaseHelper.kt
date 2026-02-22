package com.example.absensitaruna.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.absensitaruna.model.AbsensiItem

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "absensi_taruna.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_CACHE = "cache_absensi"
        const val COL_ID = "id"
        const val COL_NAMA = "nama"
        const val COL_NPT = "npt"
        const val COL_WAKTU = "waktu_absen"
        const val COL_STATUS = "status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_CACHE ("
                + "$COL_ID INTEGER PRIMARY KEY, "
                + "$COL_NAMA TEXT, "
                + "$COL_NPT TEXT, "
                + "$COL_WAKTU TEXT, "
                + "$COL_STATUS TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CACHE")
        onCreate(db)
    }

    fun insertCache(id: Int, nama: String, npt: String, waktu: String, status: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, id)
        values.put(COL_NAMA, nama)
        values.put(COL_NPT, npt)
        values.put(COL_WAKTU, waktu)
        values.put(COL_STATUS, status)

        db.insertWithOnConflict(TABLE_CACHE, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    // Fungsi ini yang tadi tidak terbaca karena kemungkinan salah letak
    fun getAllCache(): List<AbsensiItem> {
        val list = mutableListOf<AbsensiItem>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CACHE", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                val waktu = cursor.getString(cursor.getColumnIndexOrThrow(COL_WAKTU))
                val status = cursor.getString(cursor.getColumnIndexOrThrow(COL_STATUS))

                val item = AbsensiItem(id, 0, 0, waktu, status)
                list.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}