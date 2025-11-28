package com.t2404e.my_springboot.controller;

import com.t2404e.my_springboot.sevrice.StudentService;
import com.t2404e.my_springboot.sevrice.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentScoreService scoreService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        // Lấy danh sách sinh viên
        model.addAttribute("students", studentService.listAll());
        // Lấy danh sách điểm
        model.addAttribute("scores", scoreService.listAll());
        // Trả về template home.html
        return "home";
    }
}
