package com.utfpr.prova.model.mapper;

import com.utfpr.prova.model.Department;
import com.utfpr.prova.model.dto.DepartmentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    @Autowired
    private ModelMapper mapper;

    public Department toEntity(DepartmentDTO dto){
        Department department = mapper.map(dto, Department.class);
        return department;
    }

    public DepartmentDTO toDTO(Department entity){
        DepartmentDTO departmentDTO = mapper.map(entity, DepartmentDTO.class);
        return departmentDTO;
    }
}
