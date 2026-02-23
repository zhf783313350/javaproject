package com.example.demo.application.convert;

import com.example.demo.api.vo.TeacherVO;
import com.example.demo.domain.entity.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-23T13:19:43+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class TeacherConverterImpl implements TeacherConverter {

    @Override
    public TeacherVO toVO(Teacher entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherVO teacherVO = new TeacherVO();

        teacherVO.setId( entity.getId() );
        teacherVO.setName( entity.getName() );
        teacherVO.setAge( entity.getAge() );
        teacherVO.setEmail( entity.getEmail() );
        teacherVO.setAddress( entity.getAddress() );

        return teacherVO;
    }
}
