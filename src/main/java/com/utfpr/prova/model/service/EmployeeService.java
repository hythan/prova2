package com.utfpr.prova.model.service;

import com.utfpr.prova.model.Employee;
import com.utfpr.prova.model.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll(){ return employeeRepository.findAll();}

    public Optional<Employee> findById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id){
         employeeRepository.deleteById(id);
    }

    public Page<Employee> findAll(Pageable page){
        return employeeRepository.findAll(page);
    }
}
