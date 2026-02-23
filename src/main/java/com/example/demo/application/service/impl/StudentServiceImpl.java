package com.example.demo.application.service.impl;

import com.example.demo.api.vo.StudentVO;
import com.example.demo.application.convert.StudentConverter;
import com.example.demo.application.service.StudentService;
import com.example.demo.domain.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }

    @Override
    public Page<StudentVO> getStudentPage(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentConverter::toVO);
    }
}
