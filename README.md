# EduManage - Spring Boot Advanced JPA Mapping

EduManage adalah aplikasi web Sistem Manajemen Akademik sederhana yang dibangun untuk mendemonstrasikan implementasi **Advanced JPA & Hibernate Mapping** di dalam ekosistem Spring Boot. Aplikasi ini menyediakan operasi CRUD (Create, Read, Update, Delete) penuh dengan antarmuka pengguna yang bersih dan responsif.

## 🚀 Fitur Utama

- **Instruktur Management:** Mengelola data instruktur beserta detail profilnya (Relasi *One-to-One*).
- **Course Management:** Mengelola mata kuliah, menyematkan instruktur, dan mengelola ulasan/review (*One-to-Many* & *Many-to-One*).
- **Student Management:** Mengelola data siswa dan pendaftaran mereka ke berbagai mata kuliah (*Many-to-Many*).
- **Clean UI/UX:** Antarmuka bergaya *enterprise dashboard* yang dibangun menggunakan Thymeleaf dan Bootstrap 5.

## 🛠️ Tech Stack

- **Backend:** Java 17+, Spring Boot 3, Spring Data JPA
- **Database Mapping:** Hibernate
- **Frontend:** HTML5, Thymeleaf, Bootstrap 5, Bootstrap Icons
- **Database:** [Isi dengan database yang kamu gunakan, misal: MySQL / PostgreSQL / H2 In-Memory Database]
- **Build Tool:** Maven

## 📋 Konsep Relasi Database (JPA Mapping)

Project ini mengimplementasikan berbagai jenis relasi entitas:
1. `@OneToOne`: Antara `Instructor` dan `InstructorDetail`.
2. `@OneToMany` / `@ManyToOne`: Antara `Instructor` dan `Course`, serta `Course` dan `Review`.
3. `@ManyToMany`: Antara `Course` dan `Student` (Siswa dapat mengambil banyak kursus, dan kursus dapat diambil oleh banyak siswa).

## ⚙️ Cara Menjalankan Project (Local Development)

1. **Clone repository ini:**
   ```bash
   git clone [https://github.com/username-kamu/nama-repo-kamu.git](https://github.com/username-kamu/nama-repo-kamu.git)
