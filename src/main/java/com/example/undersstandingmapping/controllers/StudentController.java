package com.example.undersstandingmapping.controllers;

import com.example.undersstandingmapping.models.Student;
import com.example.undersstandingmapping.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// to mark this class as a controller.
// and return the output as json directly
@RestController
public class StudentController {

    // not a good idea to use repositories inside controllers

    // automatically wire an instance of repo to this class -> Autowired
    @Autowired
    private StudentRepository studentRepository;

    // I'  doing somehting

    @GetMapping("/students")
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homepage");
        return modelAndView;
    }

    // actual url to which you're serving the response
    @RequestMapping("/getStudents")
    public List<Student> getStudent(){

        return studentRepository.findAll();
    }

    @GetMapping("/create-student")
    public ModelAndView getStudentForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,  value="/create")
    public ModelAndView createStudent(Long id, String name, String psp) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setPsp(psp);
        studentRepository.save(student);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // Transactions 1 and 2 completely.

}
