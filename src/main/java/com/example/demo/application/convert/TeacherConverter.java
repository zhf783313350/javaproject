package com.example.demo.application.convert;

import com.example.demo.api.vo.TeacherVO;
import com.example.demo.domain.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherConverter {
    TeacherConverter INSTANCE = Mappers.getMapper(TeacherConverter.class);

    TeacherVO toVO(Teacher entity);
}
