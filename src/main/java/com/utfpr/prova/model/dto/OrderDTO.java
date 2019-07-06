package com.utfpr.prova.model.dto;

import com.utfpr.prova.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Long id;

    @NotEmpty(message = "Descrição não pode estar vazio.")
    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean done;

    private EmployeeDTO employee;


}
