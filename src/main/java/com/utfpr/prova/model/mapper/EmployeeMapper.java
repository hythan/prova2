package com.utfpr.prova.model.mapper;

import com.utfpr.prova.model.Employee;
import com.utfpr.prova.model.dto.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    @Autowired
    private ModelMapper mapper;

    public Employee toEntity(EmployeeDTO dto){
        Employee employee = mapper.map(dto, Employee.class);
        return employee;
    }

    public EmployeeDTO toDTO(Employee entity){
        EmployeeDTO employeeDTO = mapper.map(entity, EmployeeDTO.class);
        return employeeDTO;
    }
}
