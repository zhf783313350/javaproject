package com.example.demo.application.service;

import com.example.demo.api.vo.TeacherVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherVO> getTeacherPage(Pageable pageable);
}
