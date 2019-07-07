package com.utfpr.prova.controller;

import com.utfpr.prova.model.Department;
import com.utfpr.prova.model.dto.DepartmentDTO;
import com.utfpr.prova.model.mapper.DepartmentMapper;
import com.utfpr.prova.model.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.utfpr.prova.util.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
public class    DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> all(){

        List<Department> departments = this.departmentService.findAll();

        ArrayList<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department d: departments) {
            departmentDTOS.add(departmentMapper.toDTO(d));
        }

        return ResponseEntity.ok(departmentDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<DepartmentDTO>> getById(@PathVariable("id") Long id){
        Response<DepartmentDTO> response = new Response<>();

        Optional<Department> department = departmentService.findById(id);

        if (!department.isPresent()){
            response.addError("Departamento não encontrado!");
            return ResponseEntity.badRequest().body(response);
        }

        DepartmentDTO departmentDTO = departmentMapper.toDTO(department.get());
        response.setData(departmentDTO);
        return  ResponseEntity.ok(response);
    }

    @GetMapping(value = "/paginacao")
    public ResponseEntity<Response<Page<DepartmentDTO>>> findWithPage(
            @PageableDefault(page=0, size=2, direction = Sort.Direction.ASC) Pageable pageable) {

        Response<Page<DepartmentDTO>> response = new Response<>();

        Page<Department> departments = this.departmentService.findAll(pageable);
        Page<DepartmentDTO> dtoPage = departments.map(d -> departmentMapper.toDTO(d));

        response.setData(dtoPage);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/novo")
    public ResponseEntity<Response<DepartmentDTO>> save(@Valid @RequestBody DepartmentDTO departmentDTO, BindingResult result) {

        Response<DepartmentDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        if (departmentDTO.getId() != null) {
            Optional<Department> o = departmentService.findById(departmentDTO.getId());
            if (o.isPresent()) {
                response.addError("Departamento já cadastrado.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        Department department = departmentMapper.toEntity(departmentDTO);
        departmentService.save(department);

        response.setData(departmentDTO);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable("id") Long id){
        Response<String> response = new Response<>();
        Optional<Department> o = departmentService.findById(id);

        if (!o.isPresent()) {
            response.addError("Erro ao remover, departamento não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }

        this.departmentService.deleteById(id);
        response.setData("Departamento deletado com sucesso!");
        return ResponseEntity.ok(response);
    }


}
