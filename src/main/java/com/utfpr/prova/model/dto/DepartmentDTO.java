package com.utfpr.prova.model.dto;

import com.utfpr.prova.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDTO {

    private Long id;

    @NotEmpty(message = "Por favor ensira o nome do departamento.")
    private String name;

}
