package com.example.demo.api.controller;

import com.example.demo.api.vo.TeacherVO;
import com.example.demo.application.service.TeacherService;
import com.example.demo.infrastructure.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Teacher Management", description = "APIs for managing teachers")
@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Get teacher list with pagination")
    @GetMapping
    public Result<Page<TeacherVO>> getTeachers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        return Result.success(teacherService.getTeacherPage(pageable));
    }

    @Operation(summary = "Get a single teacher by ID for RPC calls")
    @GetMapping("/{id}")
    @com.alibaba.csp.sentinel.annotation.SentinelResource(value = "getTeacherById", blockHandler = "handleTeacherBlock")
    public Result<TeacherVO> getTeacherById(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        // Return a mock/fetched teacher for demonstration
        TeacherVO mockTeacher = new TeacherVO();
        mockTeacher.setId(id);
        mockTeacher.setName("Master Teacher");
        mockTeacher.setAge(45);
        return Result.success(mockTeacher);
    }

    // Sentinel Block/Fallback Handler
    public Result<TeacherVO> handleTeacherBlock(Integer id, com.alibaba.csp.sentinel.slots.block.BlockException ex) {
        TeacherVO fallbackTeacher = new TeacherVO();
        fallbackTeacher.setId(id);
        fallbackTeacher.setName("System Busy - Fallback Teacher");
        return Result.fail(503, "High traffic detected. Sentinel Circuit Breaker Activated.");
    }
}
