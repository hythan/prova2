package com.utfpr.prova.model.dto;

import com.utfpr.prova.model.Department;
import com.utfpr.prova.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    @NotEmpty(message = "Favor insira o nome do empregado.")
    private String name;

    private UserDTO user;

    private DepartmentDTO department;
}
