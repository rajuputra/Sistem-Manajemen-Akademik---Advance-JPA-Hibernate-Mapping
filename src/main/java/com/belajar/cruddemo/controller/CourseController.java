package com.belajar.cruddemo.controller;

import com.belajar.cruddemo.dao.AppDAO;
import com.belajar.cruddemo.entity.Course;
import com.belajar.cruddemo.entity.Review;
import com.belajar.cruddemo.entity.Student;
import com.belajar.cruddemo.entity.Instructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final AppDAO appDAO;

    public CourseController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", appDAO.findAllCourses());
        return "courses/list";
    }

    @GetMapping("/{id}")
    public String courseDetail(@PathVariable int id, Model model) {
        Course course = appDAO.findCourseAndReviewsByCourseId(id);
        if (course == null) {
            return "redirect:/courses?error=notfound";
        }
        Course courseWithStudents = appDAO.findCourseAndStudentByCourseId(id);
        model.addAttribute("course", courseWithStudents);
        model.addAttribute("allStudents", appDAO.findAllStudents());
        return "courses/detail";
    }

    @PostMapping("/{courseId}/reviews")
    public String addReview(@PathVariable int courseId, @RequestParam String comment) {
        Course course = appDAO.findCourseById(courseId);
        course.addReview(new Review(comment));
        appDAO.Update(course);
        return "redirect:/courses/" + courseId;
    }

    @PostMapping("/{courseId}/assign-student")
    public String assignStudent(@PathVariable int courseId, @RequestParam int studentId) {
        Course course = appDAO.findCourseById(courseId);
        Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
        if (student != null) {
            course.addStudent(student);
            appDAO.Update(course);
        }
        return "redirect:/courses/" + courseId;
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        appDAO.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("instructors", appDAO.findAllInstructors());
        model.addAttribute("allStudents", appDAO.findAllStudents());
        return "courses/form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course,
                             @RequestParam int instructorId,
                             @RequestParam(required = false) List<Integer> studentIds) {
        Instructor instructor = appDAO.findInstructorById(instructorId);
        instructor.add(course);          // bidirectional instructor ↔ course
        appDAO.save(course);             // persist course (instructor sudah ada)

        if (studentIds != null && !studentIds.isEmpty()) {
            for (Integer studentId : studentIds) {
                Student student = appDAO.findStudentAndCoursesByStudentId(studentId);
                if (student != null) {
                    student.addCourse(course);   // bidirectional, memanggil course.addStudent(this)
                }
            }
            appDAO.Update(course);       // sync perubahan relasi student ke database
        }
        return "redirect:/courses";
    }
}