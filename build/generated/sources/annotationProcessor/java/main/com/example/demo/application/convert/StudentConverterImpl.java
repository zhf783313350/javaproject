package com.example.demo.application.convert;

import com.example.demo.api.vo.StudentVO;
import com.example.demo.domain.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-23T11:56:56+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class StudentConverterImpl implements StudentConverter {

    @Override
    public StudentVO toVO(Student entity) {
        if ( entity == null ) {
            return null;
        }

        StudentVO studentVO = new StudentVO();

        studentVO.setId( entity.getId() );
        studentVO.setName( entity.getName() );
        studentVO.setAge( entity.getAge() );
        studentVO.setEmail( entity.getEmail() );
        studentVO.setAddress( entity.getAddress() );
        studentVO.setClassid( entity.getClassid() );
        studentVO.setTime( entity.getTime() );

        return studentVO;
    }
}
