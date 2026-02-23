package com.example.demo.application.service.impl;

import com.example.demo.api.vo.TeacherVO;
import com.example.demo.application.convert.TeacherConverter;
import com.example.demo.application.service.TeacherService;
import com.example.demo.domain.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherConverter teacherConverter) {
        this.teacherRepository = teacherRepository;
        this.teacherConverter = teacherConverter;
    }

    @Override
    public Page<TeacherVO> getTeacherPage(Pageable pageable) {
        return teacherRepository.findAll(pageable).map(teacherConverter::toVO);
    }
}
