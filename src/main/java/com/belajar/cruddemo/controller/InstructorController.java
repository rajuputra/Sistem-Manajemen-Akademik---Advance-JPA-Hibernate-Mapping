package com.belajar.cruddemo.controller;

import com.belajar.cruddemo.dao.AppDAO;
import com.belajar.cruddemo.entity.Course;
import com.belajar.cruddemo.entity.Instructor;
import com.belajar.cruddemo.entity.InstructorDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final AppDAO appDAO;

    public InstructorController(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    @GetMapping
    public String listInstructors(Model model) {
        model.addAttribute("instructors", appDAO.findAllInstructors());
        return "instructors/list";
    }


    @GetMapping("/{id}")
    public String instructorDetail(@PathVariable int id, Model model) {
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
        if (instructor == null) {
            return "redirect:/instructors?error=notfound";
        }
        model.addAttribute("instructor", instructor);
        return "instructors/detail";
    }


    @PostMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable int id) {
        appDAO.deleteInstructorById(id);
        return "redirect:/instructors";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructors/form";
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute Instructor instructor,
                                 @RequestParam String youtubeChannel,
                                 @RequestParam String hobby) {
        InstructorDetail detail = new InstructorDetail(youtubeChannel, hobby);
        instructor.setInstructorDetail(detail);
        appDAO.save(instructor); // cascade akan simpan detail juga
        return "redirect:/instructors";
    }
}