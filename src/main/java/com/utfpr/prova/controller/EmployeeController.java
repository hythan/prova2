package com.utfpr.prova.controller;

import com.utfpr.prova.model.Employee;
import com.utfpr.prova.model.dto.EmployeeDTO;
import com.utfpr.prova.model.mapper.EmployeeMapper;
import com.utfpr.prova.model.service.EmployeeService;
import com.utfpr.prova.security.JwtUser;
import com.utfpr.prova.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/paginacao")
    public ResponseEntity<Response<Page<EmployeeDTO>>> findWithPage(
            @PageableDefault(page=0, size=2, direction = Sort.Direction.ASC) Pageable pageable) {

        Response<Page<EmployeeDTO>> response = new Response<>();

        Page<Employee> employees = this.employeeService.findAll(pageable);
        Page<EmployeeDTO> dtoPage = employees.map(d -> employeeMapper.toDTO(d));

        response.setData(dtoPage);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<EmployeeDTO>> getById(@PathVariable("id") Long id){
        Response<EmployeeDTO> response = new Response<>();

        Optional<Employee> employee = employeeService.findById(id);

        if (!employee.isPresent()){
            response.addError("Departamento não encontrado!");
            return ResponseEntity.badRequest().body(response);
        }

        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee.get());
        response.setData(employeeDTO);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/novo")
    public ResponseEntity<Response<EmployeeDTO>> save(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {

        Response<EmployeeDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        if (employeeDTO.getId() != null) {
            Optional<Employee> o = employeeService.findById(employeeDTO.getId());
            if (o.isPresent()) {
                response.addError("Empregado já cadastrado.");
                return ResponseEntity.badRequest().body(response);
            }
        }

        Employee employee = employeeMapper.toEntity(employeeDTO);
        employeeService.save(employee);
        response.setData(employeeDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto, BindingResult result) {

        JwtUser currentUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response<EmployeeDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Employee> o = employeeService.findById(id);
        if (!o.isPresent()) {
            response.addError("Empregado não encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        if (!currentUser.getId().equals(o.get().getUser().getId())) {
            response.addError("Você pode autorizar apenas seu próprio usuário.");
            return ResponseEntity.badRequest().body(response);
        }
        dto.setId(id);
        Employee employee = employeeMapper.toEntity(dto);
        employeeService.save(employee);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }
}
