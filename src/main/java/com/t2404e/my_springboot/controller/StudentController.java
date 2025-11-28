package com.t2404e.my_springboot.controller;

import com.t2404e.my_springboot.entity.Student;
import com.t2404e.my_springboot.sevrice.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.listAll()); // danh sách mới nhất
        return "students"; // template students.html
    }

    // Hiển thị form thêm sinh viên mới
    @GetMapping("/add")
    public String addForm(Model model) {
        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new Student());
        }
        // Chỉ có nút Back to Home trong add-student.html
        return "add-student";
    }

    // Lưu sinh viên mới và hiển thị trực tiếp trên trang danh sách
    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("student") Student student,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            return "add-student"; // nếu có lỗi, vẫn quay lại form
        }

        try {
            service.save(student); // lưu sinh viên mới
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu sinh viên: " + e.getMessage());
            return "add-student";
        }

        // Sau khi lưu, load danh sách mới và trả về students.html trực tiếp
        model.addAttribute("list", service.listAll());
        model.addAttribute("successMessage", "Đã thêm sinh viên: " + student.getFullName());

        return "students"; // render trực tiếp template students.html
    }
}
