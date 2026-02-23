package com.example.demo.application.client;

import com.example.demo.api.vo.TeacherVO;
import com.example.demo.infrastructure.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Connects dynamically to the "teacher-service" registered in Nacos
@FeignClient(name = "teacher-service", path = "/api/v1/teachers")
public interface TeacherFeignClient {

    @GetMapping("/{id}")
    Result<TeacherVO> getTeacherById(@PathVariable("id") Integer id);

}
