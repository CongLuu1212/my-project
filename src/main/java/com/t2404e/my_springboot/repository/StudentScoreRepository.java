package com.t2404e.my_springboot.repository;

import com.t2404e.my_springboot.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {
}
