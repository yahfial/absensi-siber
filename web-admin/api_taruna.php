<?php
header('Content-Type: application/json');
require 'koneksi.php';

$method = $_SERVER['REQUEST_METHOD'];

if ($method == 'GET') {
    // Read: Mengambil data taruna
    $sql = "SELECT id, npt, nama, role FROM users WHERE role='taruna'";
    $result = $conn->query($sql);
    $data = array();
    
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $data[] = $row;
        }
    }
    echo json_encode(["status" => "success", "data" => $data]);
} 
elseif ($method == 'POST') {
    // Create: Validasi Server-Side dan Menambah data
    $npt = isset($_POST['npt']) ? trim($_POST['npt']) : '';
    $nama = isset($_POST['nama']) ? trim($_POST['nama']) : '';
    
    if(empty($npt) || empty($nama)) {
        echo json_encode(["status" => "error", "message" => "NPT dan Nama tidak boleh kosong!"]);
        exit;
    }

    // Default password untuk taruna baru
    $password = password_hash('taruna123', PASSWORD_DEFAULT); 
    
    // Mencegah SQL Injection dasar dengan Prepared Statement
    $stmt = $conn->prepare("INSERT INTO users (npt, nama, password, role) VALUES (?, ?, ?, 'taruna')");
    $stmt->bind_param("sss", $npt, $nama, $password);
    
    if ($stmt->execute()) {
        echo json_encode(["status" => "success", "message" => "Data Taruna berhasil ditambahkan"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Gagal menyimpan data: " . $stmt->error]);
    }
    $stmt->close();
}
$conn->close();
?>