php
$host = 127.0.0.1;
$user = root;
$pass = ;
$db   = db_absensi_taruna;
$port = 3307;  Sesuaikan dengan port MySQL kamu yang berhasil jalan

$conn = new mysqli($host, $user, $pass, $db, $port);

if ($conn-connect_error) {
    die(json_encode([status = error, message = Koneksi gagal  . $conn-connect_error]));
}
