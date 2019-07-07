package com.utfpr.prova.model.service;

import com.utfpr.prova.model.Department;
import com.utfpr.prova.model.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Optional<Department> findById(Long id){
        return departmentRepository.findById(id);
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public void deleteById(Long id){
        departmentRepository.deleteById(id);
    }

    public Page<Department> findAll(Pageable page){
        return departmentRepository.findAll(page);
    }
}
