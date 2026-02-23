package com.example.demo.application.service;

import com.example.demo.api.vo.StudentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<StudentVO> getStudentPage(Pageable pageable);
}
