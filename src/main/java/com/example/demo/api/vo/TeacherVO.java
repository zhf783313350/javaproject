package com.example.demo.api.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class TeacherVO implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String address;
}
