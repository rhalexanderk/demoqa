# demoqa

📝 Student Registration Form Automation (Katalon)
Proyek ini berisi Test Case otomatis untuk memverifikasi fungsionalitas dan validasi formulir pendaftaran mahasiswa di DemoQA Forms Page menggunakan Katalon Studio.

🚀 Prasyarat (Requirements)Anda harus memiliki perangkat lunak berikut terinstal:
- Katalon Studio: Versi 8.x atau lebih baru.
- Java Development Kit (JDK): Versi 8 atau lebih baru.
- Web Browser: Chrome atau Firefox.

🛠️ Cara Menjalankan TestProyek ini dijalankan melalui Test Suite utama yang menggunakan Data-Driven Testing (DDT).
1. Eksekusi Melalui Katalon Studio
   - Buka proyek di Katalon Studio.
   - Buka Test Suites > TS_Forms_Registration_DDT.
   - Klik tombol Run dan pilih Browser (Chrome disarankan).
2. Output dan Status TestSkenario Positif:
   - Akan berstatus PASS (HIJAU) jika submission berhasil dan data di modal akurat.
   - Skenario Negatif (Validasi Berhasil): Akan berstatus PASS (HIJAU) karena form berhasil gagal submit.
   - Skenario Negatif (Bug Kritis): Akan berstatus PASS (HIJAU), tetapi Log WARNING yang jelas akan dicatat di laporan (e.g., "GAGAL KRITIS. Expected: GAGAL submit"), menandakan bug validasi.

📂 Struktur Utama Path Deskripsi Test
Suites/TS_DemoQA.ts File eksekusi utama (menggunakan data dari Data Files).
Include/features/Berisi file Gherkin (.feature) yang mendefinisikan skenario.
Include/scripts/groovy/Berisi file Groovy yang mengimplementasikan logika pengujian.
