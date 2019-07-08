package com.utfpr.prova.controller;

import com.utfpr.prova.model.Employee;
import com.utfpr.prova.model.dto.EmployeeDTO;
import com.utfpr.prova.model.mapper.EmployeeMapper;
import com.utfpr.prova.model.service.EmployeeService;
import com.utfpr.prova.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/empregados")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> all(){

        List<Employee> employees = this.employeeService.findAll();

        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee d: employees) {
            employeeDTOS.add(employeeMapper.toDTO(d));
        }

        return ResponseEntity.ok(employeeDTOS);
    }
}
