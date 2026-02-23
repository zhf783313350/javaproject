package com.example.demo;
import java.util.HashMap;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    // GET /hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
    // POST /user 接收 JSON 并返回 JSON
    @PostMapping("/user")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> user) {
        // 返回原数据 + 添加 status 字段
        Map<String, Object> response = new HashMap<>(user);
        response.put("status", "success");
        return response;
    }
    @Autowired
    private StudentRepository studentRepository;
    // 查询学生列表
    @GetMapping("/students")
    public Map<String, Object> getStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("pageSize", pageSize);
        response.put("data", studentPage.getContent());
        response.put("totalElements", studentPage.getTotalElements());
        response.put("totalPages", studentPage.getTotalPages());   
        return response;
    }
    @Autowired
    private TeacherRepository teacherRepository;
    @GetMapping("/teachers")
    public Map<String, Object> getTeachers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, pageSize);
        Page<Teachers> teacherPage = teacherRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("page", page);
        response.put("pageSize", pageSize);
        response.put("data", teacherPage.getContent());
        response.put("totalElements", teacherPage.getTotalElements());
        response.put("totalPages", teacherPage.getTotalPages());
        return response;
    }
}
