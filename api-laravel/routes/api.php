<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AbsensiController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
*/

// Rute bawaan Laravel (biarkan saja)
Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

// Rute API Absensi buatanmu
Route::get('/absensi', [AbsensiController::class, 'index']);