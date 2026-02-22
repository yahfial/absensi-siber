<?php
namespace App\Http\Controllers;

use App\Models\Absensi;
use Illuminate\Http\Request;

class AbsensiController extends Controller
{
    public function index()
    {
        // Mengambil semua data dari tabel absensi
        $data = Absensi::all();
        return response()->json([
            'status' => 'success',
            'data' => $data
        ]);
    }

    // Kamu bisa menambahkan fungsi store, update, dan destroy di sini nanti
}