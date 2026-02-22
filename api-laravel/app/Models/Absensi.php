<?php
namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Absensi extends Model
{
    protected $table = 'absensi'; // Menunjuk ke tabel manual kita
    public $timestamps = false; // Karena kita tidak pakai kolom created_at/updated_at bawaan
    protected $fillable = ['jadwal_id', 'user_id', 'waktu_absen', 'status'];
}