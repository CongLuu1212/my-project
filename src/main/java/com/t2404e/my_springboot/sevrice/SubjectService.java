package com.t2404e.my_springboot.sevrice;

import com.t2404e.my_springboot.entity.Subject;
import com.t2404e.my_springboot.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository repo;

    // Constructor injection (tốt hơn @Autowired trực tiếp)
    public SubjectService(SubjectRepository repo) {
        this.repo = repo;
    }

    // Lấy tất cả môn học
    public List<Subject> listAll() {
        return repo.findAll();
    }

    // Lưu hoặc cập nhật môn học
    @Transactional
    public void save(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject không được null");
        }
        repo.save(subject);
    }

    // Tìm môn học theo ID
    public Optional<Subject> findById(Integer id) {
        return repo.findById(id);
    }

    // Xoá môn học theo ID
    @Transactional
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
