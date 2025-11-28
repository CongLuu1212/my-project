package com.t2404e.my_springboot.sevrice;

import com.t2404e.my_springboot.entity.Student;
import com.t2404e.my_springboot.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    // Constructor injection
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // Lấy tất cả sinh viên
    public List<Student> listAll() {
        return repo.findAll();
    }

    // Lưu hoặc cập nhật sinh viên
    @Transactional
    public void save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student không được null");
        }
        repo.save(student);
    }

    // Tìm sinh viên theo ID
    public Optional<Student> findById(Integer id) {
        return repo.findById(id);
    }

    // Xoá sinh viên theo ID
    @Transactional
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
