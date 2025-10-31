package com.StudentsManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.StudentsManagementSystem.entity.Student;
import com.StudentsManagementSystem.service.StudentService;

@Controller
public class HomeController {
	
	@Autowired
	private StudentService service;
	
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/Students")
    public String getAllStudent(Model model) {
    	model.addAttribute("Students", service.getAllStudent());
    	return "Students";
    }
    
    @GetMapping("/Students/new")
    public String createStudentForm(Model model) {
    	Student student = new Student();
    	model.addAttribute("student", student);
    	return "create-student";
    }

    @PostMapping("/Students/add")
    public String saveStudent(@ModelAttribute("student") Student student){
        service.saveStudent(student);
        return "redirect:/Students";
    }
    
    @GetMapping("/Students/edit/{id}")
    public String updateStudentForm(@PathVariable("id") int id, Model model) {
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/Students/edit")
    public String updateStudent(@ModelAttribute("student") Student student) {
        service.saveStudent(student); 
        return "redirect:/Students";
    }

    @GetMapping("/Students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/Students";
    }
}
