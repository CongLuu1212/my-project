package com.t2404e.my_springboot.controller;

import com.t2404e.my_springboot.entity.StudentScore;
import com.t2404e.my_springboot.entity.Student;
import com.t2404e.my_springboot.entity.Subject;
import com.t2404e.my_springboot.sevrice.StudentScoreService;
import com.t2404e.my_springboot.sevrice.StudentService;
import com.t2404e.my_springboot.sevrice.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private StudentScoreService scoreService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    // Chỉ giữ form thêm điểm
    @GetMapping("/add")
    public String addForm(Model model) {
        StudentScore score = new StudentScore();
        score.setStudent(new Student());
        score.setSubject(new Subject());
        model.addAttribute("score", score);
        model.addAttribute("students", studentService.listAll());
        model.addAttribute("subjects", subjectService.listAll());
        return "add-score"; // templates/add-score.html
    }

    // Lưu điểm mới và redirect về Home
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("score") StudentScore score,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("students", studentService.listAll());
            model.addAttribute("subjects", subjectService.listAll());
            return "add-score";
        }

        try {
            scoreService.save(score);
        } catch (Exception e) {
            model.addAttribute("students", studentService.listAll());
            model.addAttribute("subjects", subjectService.listAll());
            model.addAttribute("errorMessage", "Lỗi khi lưu điểm: " + e.getMessage());
            return "add-score";
        }

        return "redirect:/home"; // redirect về Home sau khi lưu
    }
}
