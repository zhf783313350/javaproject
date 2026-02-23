package com.example.demo.api.controller;

import com.example.demo.api.vo.StudentVO;
import com.example.demo.application.service.StudentService;
import com.example.demo.infrastructure.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Student Management", description = "APIs for managing students")
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;
    private final com.example.demo.application.client.TeacherFeignClient teacherFeignClient;

    public StudentController(StudentService studentService,
            com.example.demo.application.client.TeacherFeignClient teacherFeignClient) {
        this.studentService = studentService;
        this.teacherFeignClient = teacherFeignClient;
    }

    @Operation(summary = "Get student list with pagination")
    @GetMapping
    public Result<Page<StudentVO>> getStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        return Result.success(studentService.getStudentPage(pageable));
    }

    @Operation(summary = "Get the teacher assigned to the student (Demonstrates OpenFeign RPC)")
    @GetMapping("/{id}/teacher")
    public Result<com.example.demo.api.vo.TeacherVO> getStudentTeacher(
            @org.springframework.web.bind.annotation.PathVariable Integer id) {
        // Here we simulate getting a teacher ID assigned to this student
        Integer teacherId = id + 10;
        // Make an HTTP RPC call to the teacher-service via Nacos + OpenFeign
        return teacherFeignClient.getTeacherById(teacherId);
    }
}
