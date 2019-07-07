package com.utfpr.prova.controller;

import com.utfpr.prova.model.Department;
import com.utfpr.prova.model.dto.DepartmentDTO;
import com.utfpr.prova.model.mapper.DepartmentMapper;
import com.utfpr.prova.model.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.utfpr.prova.util.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController(value = "/api/departamentos")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Value("2")
    private int paginationAmount;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> all(){

        List<Department> departments = this.departmentService.findAll();

        ArrayList<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department d: departments) {
            departmentDTOS.add(departmentMapper.toDTO(d));
        }

        return ResponseEntity.ok(departmentDTOS);
    }

    @GetMapping(value = "/paginacao-departamentos")
    public ResponseEntity<Response<List<DepartmentDTO>>> findAllPagination(
            @RequestParam(value = "pag", defaultValue = "0") int page,
            @RequestParam(value = "ord", defaultValue = "name") String order,
            @RequestParam(value = "dir", defaultValue = "ASC") String direction) {


        Response<List<DepartmentDTO>> response = new Response<>();
        PageRequest pageRequest = PageRequest.of(page, this.paginationAmount, Sort.Direction.valueOf(direction), order);

        Page<Department> departments = this.departmentService.findAll(pageRequest);
        ArrayList<DepartmentDTO> dtos = new ArrayList<>();

        for (Department d: departments
             ) {
            dtos.add(departmentMapper.toDTO(d));
        }

        Page<DepartmentDTO> departmentDTOS = (Page<DepartmentDTO>) dtos;
        response.setData(departmentDTOS.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id, BindingResult result){
        Response<DepartmentDTO> response = new Response<>();

        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Department> department = departmentService.findById(id);

        if (!department.isPresent()){
            response.addError("Departamento não encontrado!");
            return ResponseEntity.badRequest().body(response);
        }

        DepartmentDTO departmentDTO = departmentMapper.toDTO(department.get());
        return  ResponseEntity.ok(departmentDTO);
    }

    @PostMapping
    public Department save(@Validated @RequestBody  DepartmentDTO departmentDTO){
        Department department = departmentMapper.toEntity(departmentDTO);
        return departmentService.save(department);
    }





}
