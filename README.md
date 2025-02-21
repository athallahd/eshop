# E-Shop
###### Athallah Damar Jiwanto - B - 2306245024

### Module 1
<details>
<summary><b>Reflection 1</b></summary>

> 1. You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

- **Menerapkan Clean Code Principles**:
  - Penamaan yang jelas → Variabel dan metode diberi nama sesuai fungsinya agar mudah dipahami.
  - Satu fungsi satu tugas → Setiap metode hanya menangani satu proses untuk menjaga keterbacaan kode.
  - DRY (Don't Repeat Yourself) → Menghindari duplikasi kode dengan membuat fungsi yang dapat digunakan kembali.
  - Konsistensi kode → Struktur kode seragam dan mengikuti standar yang diterapkan.
  - Error handling → Kesalahan yang mungkin terjadi sudah ditangani dengan baik agar tidak menyebabkan crash.

- **Menerapkan Secure Coding Practices**:
  - Validasi input → Mencegah input kosong atau tidak valid untuk menjaga integritas data.
  - Manajemen dependensi → Menggunakan _library_ resmi dan versi terbaru untuk keamanan yang lebih baik.
  - Membatasi akses API → Menggunakan HTTP _method_ yang sesuai untuk mencegah penyalahgunaan.

</details>

<details>
<summary><b>Reflection 2</b></summary>

> 1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

Dengan menulis unit test, saya merasa lebih percaya diri terhadap kualitas kode karena setiap fungsi telah diuji dengan baik sebelum digunakan dalam pengembangan lebih lanjut. Unit test membantu memastikan bahwa perubahan dalam kode tidak menyebabkan error yang tidak terduga. Jumlah unit test dalam satu kelas bergantung pada kompleksitas fitur yang diuji, tetapi idealnya setiap metode utama memiliki setidaknya satu atau lebih pengujian untuk mencakup berbagai skenario yang mungkin terjadi. Untuk memastikan cakupan pengujian sudah cukup, kita dapat menggunakan code coverage sebagai metrik yang menunjukkan sejauh mana kode telah diuji oleh unit test. Namun, mencapai 100% code coverage tidak selalu berarti bahwa kode terbebas dari bug, karena ada kemungkinan skenario edge case yang tidak terdeteksi. Oleh karena itu, selain mengejar angka code coverage yang tinggi, penting juga untuk menguji berbagai kondisi ekstrem dan kasus yang jarang terjadi guna meningkatkan keandalan serta stabilitas kode. Dengan pendekatan ini, kita dapat lebih yakin bahwa kode yang ditulis akan bekerja dengan baik dalam berbagai situasi dan lebih siap menghadapi potensi masalah di masa mendatang.

> 2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
     What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

Membuat functional test class baru dengan setup dan variabel yang sama bukanlah praktik clean code karena mengulangi kode yang sebenarnya bisa digunakan kembali. Pengulangan ini melanggar prinsip DRY (Don't Repeat Yourself), yang dapat menyebabkan kode menjadi lebih sulit dikelola dan meningkatkan risiko inkonsistensi saat ada perubahan. Jika setiap functional test suite memiliki setup yang sama, maka ketika ada pembaruan dalam prosedur setup, semua class yang memilikinya harus diperbarui secara manual, yang bisa menjadi tidak efisien. Selain itu, duplikasi kode juga dapat menyulitkan proses debugging karena perubahan kecil di satu bagian mungkin perlu diterapkan di beberapa tempat.
Untuk meningkatkan kebersihan kode, solusi yang bisa diterapkan adalah dengan membuat base test class yang berisi setup umum, sehingga test class lain cukup mewarisi class ini tanpa perlu menulis ulang konfigurasi. Selain itu, penggunaan utility methods untuk operasi yang sering digunakan juga dapat membantu menghindari duplikasi dan meningkatkan keterbacaan kode. Dengan cara ini, kode menjadi lebih rapi, efisien, dan mudah diperbarui jika ada perubahan, serta lebih fleksibel untuk pengembangan selanjutnya. Mengadopsi pendekatan ini tidak hanya meningkatkan kualitas kode, tetapi juga mempermudah kolaborasi antar developer dalam menulis dan memahami functional test.

</details>

### Module 2
<details>
<summary><b>Reflection 1</b></summary>

> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Masalah utama yang ditemukan adalah pengujian `findById()` yang belum mencakup skenario ketika ada lebih dari satu iterasi 
dalam pencarian. Jika produk dengan ID tertentu tidak ditemukan pada iterasi pertama, kode seharusnya melanjutkan pencarian 
ke elemen berikutnya. Hal ini belum diuji dengan baik, sehingga saya menambahkan test case untuk memastikan bahwa `findById()` 
dapat menangani skenario tersebut dengan benar. Selain itu, saya juga menghapus modifier `public` pada metode dalam interface. 
Dalam Java, metode dalam interface sudah bersifat `public` secara default, jadi menambahkan modifier tersebut tidak diperlukan 
dan saya mengubahnya sesuai dengan best practice.

> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Proses CI/CD yang saya terapkan telah memenuhi standar Continuous Integration dan Continuous Deployment karena seluruh 
proses berjalan secara otomatis dari pengujian hingga deployment. Setiap commit atau pull request langsung memicu unit test, 
analisis kode, serta alat seperti PMD, Scorecard, dan JaCoCo untuk memastikan kualitas kode tetap terjaga. 
Jika ada kesalahan atau pelanggaran standar, sistem memberikan umpan balik cepat, sehingga perbaikan dapat dilakukan sebelum 
kode dideploy. Selain itu, pipeline ini juga menangani proses build dan deployment ke Koyeb, memastikan aplikasi selalu dalam 
kondisi stabil tanpa perlu intervensi manual. Dengan sistem ini, pengembangan menjadi lebih efisien, minim kesalahan, 
serta memastikan kode yang dirilis selalu dalam kondisi optimal.

</details>