package com.t2404e.my_springboot.sevrice;

import com.t2404e.my_springboot.entity.StudentScore;
import com.t2404e.my_springboot.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentScoreService {

    private final StudentScoreRepository repo;

    // Constructor injection (tốt hơn @Autowired trực tiếp)
    public StudentScoreService(StudentScoreRepository repo) {
        this.repo = repo;
    }

    // Lấy tất cả StudentScore
    public List<StudentScore> listAll() {
        return repo.findAll();
    }

    // Lưu hoặc cập nhật StudentScore
    @Transactional
    public void save(StudentScore score) {
        if (score == null) {
            throw new IllegalArgumentException("StudentScore không được null");
        }
        repo.save(score);
    }
}
