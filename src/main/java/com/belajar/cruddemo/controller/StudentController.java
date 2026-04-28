package com.belajar.cruddemo.controller;

import com.belajar.cruddemo.dao.AppDAO;
import com.belajar.cruddemo.entity.Course;
import com.belajar.cruddemo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final AppDAO appDAO;

    public StudentController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", appDAO.findAllStudents());
        return "students/list";
    }

    @GetMapping("/{id}")
    public String studentDetail(@PathVariable int id, Model model) {
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
        if (student == null) {
            return "redirect:/students?error=notfound";
        }
        model.addAttribute("student", student);
        return "students/detail";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        appDAO.deleteStudentById(id);
        return "redirect:/students";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("allCourses", appDAO.findAllCourses());
        return "students/form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student,
                              @RequestParam(required = false) List<Integer> courseIds) {
        appDAO.save(student); // simpan dulu agar dapat ID
        if (courseIds != null && !courseIds.isEmpty()) {
            for (Integer courseId : courseIds) {
                Course course = appDAO.findCourseById(courseId);
                if (course != null) {
                    course.addStudent(student);   // maintain bidirectional
                    appDAO.Update(course);
                }
            }
        }
        return "redirect:/students";
    }
}