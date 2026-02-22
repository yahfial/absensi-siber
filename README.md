\# Sistem Absensi Kelas Berbasis Web \& Mobile (REST API) 🎓



Proyek ini adalah implementasi sistem absensi akademik dengan arsitektur \*Client-Server\* (n-tier) yang dibangun untuk memenuhi tugas Ujian Akhir Semester (UAS) mata kuliah Pemrograman Lanjutan di Politeknik Siber dan Sandi Negara.



\## 👥 Identitas Tim (Kelompok 6)

\* \*\*Program Studi:\*\* Rekayasa Keamanan Siber

\* \*\*Kelas:\*\* II Rekayasa Keamanan Siber B

\* \*\*Anggota Tim:\*\*

&nbsp; 1. Eulia Radifa Meilinawati (2423102020)

&nbsp; 2. Michael Ridho Waster Pakpahan (2423102045)

&nbsp; 3. Yahfi Al Farisy (2423102087)



\## 🛠️ Arsitektur \& Teknologi yang Digunakan

Sistem ini memisahkan antara \*Backend\* (Penyedia API) dan \*Frontend\* (Konsumen API):

1\. \*\*Web Admin (Frontend Statis \& Dinamis):\*\* HTML5, CSS3, JS, dan PHP Native (Operasi CRUD).

2\. \*\*REST API (Backend):\*\* Framework Laravel (PHP) dengan respons format JSON.

3\. \*\*Aplikasi Mobile (Client):\*\* Android Native menggunakan Kotlin (Arsitektur MVVM).

4\. \*\*Database:\*\* MySQL (Relasional 3NF) \& SQLite (Untuk \*Local Cache/Offline Mode\* di Android).



\## 📂 Struktur Repositori

\* `/database` : Berisi file DDL \& DML (`.sql`) untuk skema dan \*dummy data\* MySQL.

\* `/web-admin` : Berisi antarmuka web administrator (PHP Native).

\* `/api-laravel`: Berisi kerangka kerja Laravel pengelola REST API.

\* `/mobile-android`: Berisi \*source code\* aplikasi Android Studio (Kotlin).



\## 🚀 Fitur Unggulan

\* \*\*Sinkronisasi Asinkron:\*\* Aplikasi \*mobile\* mengonsumsi data secara \*real-time\* via REST API menggunakan Retrofit.

\* \*\*Resiliensi Jaringan (Offline Mode):\*\* Memanfaatkan SQLite, aplikasi Android tetap dapat menampilkan data absensi historis meskipun tidak ada koneksi internet.

\* \*\*Keamanan Lapis Dasar:\*\* Implementasi validasi sisi klien/server, \*password hashing\* (Bcrypt), dan perlindungan terhadap \*SQL Injection\*.

