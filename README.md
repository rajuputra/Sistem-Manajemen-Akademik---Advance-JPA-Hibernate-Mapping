# 🎓 EduManage - Advanced JPA & Hibernate Mapping

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://www.oracle.com/java/)
[![Hibernate](https://img.shields.io/badge/Hibernate-ORM-blue.svg)](https://hibernate.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

**EduManage** adalah platform demonstrasi sistem manajemen akademik yang dirancang untuk mengeksplorasi teknik pemetaan **Advanced JPA & Hibernate** yang kompleks. Proyek ini mengimplementasikan berbagai pola relasi database modern menggunakan **Java 25** dan **Spring Boot 4**.

---

## 📸 Project Showcase

### 🏠 Dashboard Utama
Tampilan antarmuka yang bersih dan intuitif untuk navigasi seluruh sistem.
![Homepage](screenshots/homepage.png)

<details>
<summary><b>🔍 Lihat Detail Showcase Lainnya</b></summary>

#### 👨‍🏫 Manajemen Instruktur
Sistem manajemen instruktur yang mengimplementasikan relasi `@OneToOne` dengan profil detail.
| Daftar Instruktur | Detail Instruktur |
| :---: | :---: |
| ![Daftar Instructor](screenshots/daftar%20instructor.png) | ![Detail Instructor](screenshots/detail%20instructor.png) |

#### 📚 Manajemen Kursus & Siswa
Implementasi relasi `@OneToMany` (Course to Review) dan `@ManyToMany` (Course to Student).
| Detail Kursus | Detail Siswa |
| :---: | :---: |
| ![Detail Course](screenshots/detail%20course.png) | ![Detail Student](screenshots/detail%20student.png) |

#### ➕ Formulir Input
UI yang responsif untuk menambahkan data baru ke dalam sistem.
![Add Instructor](screenshots/add%20instructor.png)
</details>

---

## 🚀 Fitur Utama

- **Advanced Relationship Mapping:** Implementasi mendalam `@OneToOne`, `@OneToMany`, `@ManyToOne`, dan `@ManyToMany`.
- **Bidirectional Navigation:** Semua entitas mendukung navigasi dua arah yang dikelola dengan benar untuk konsistensi data.
- **Cascade Operations:** Penggunaan `CascadeType` yang tepat (Persist, Merge, Remove, dll.) untuk manajemen siklus hidup entitas.
- **Fetch Strategy Optimization:** Demonstrasi penggunaan `Eager` vs `Lazy` loading untuk performa maksimal.
- **Modern UI:** Dibangun dengan **Thymeleaf** dan **Bootstrap 5** dengan estetika *enterprise*.

---

## 🛠️ Tech Stack

- **Core:** Java 25 (Latest JDK Features)
- **Framework:** Spring Boot 4.0.6
- **Persistence:** Spring Data JPA / Hibernate ORM
- **Database:** MySQL 8.0+
- **Template Engine:** Thymeleaf
- **Styling:** Bootstrap 5 & Bootstrap Icons
- **Build Tool:** Maven

---

## 📊 Arsitektur Relasi JPA

Proyek ini mendemonstrasikan 4 jenis relasi utama:

1.  **`Instructor` ↔ `InstructorDetail` (`@OneToOne`)**
    - Berbagi siklus hidup melalui `CascadeType.ALL`.
2.  **`Instructor` → `Course` (`@OneToMany`)**
    - Satu instruktur dapat memiliki banyak kursus. Menggunakan *Lazy Loading*.
3.  **`Course` → `Review` (`@OneToMany`)**
    - Kursus dapat memiliki banyak ulasan dari siswa (Unidirectional & Bidirectional examples).
4.  **`Course` ↔ `Student` (`@ManyToMany`)**
    - Relasi kompleks di mana siswa dapat mengambil banyak kursus dan sebaliknya.

---

## ⚙️ Instalasi & Cara Menjalankan

### Persiapan
- JDK 25 installed
- MySQL Server running
- Maven installed

### Langkah-langkah
1. **Clone Repository**
   ```bash
   git clone https://github.com/rajuputra/edumanage-advanced-jpa.git
   ```

2. **Konfigurasi Database**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. **Jalankan Aplikasi**
   ```bash
   mvn spring-boot:run
   ```
   Akses di: `http://localhost:8080`

---

## 👨‍💻 Penulis

**Raju Putra Dermawan**
*Software Engineer | Backend Developer*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat&logo=linkedin)](https://www.linkedin.com/in/raju-putra-dermawan-244919220/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=flat&logo=github)](https://github.com/rajuputra)

---
<p align="center">Dibuat dengan ❤️ untuk komunitas Java Indonesia</p>
