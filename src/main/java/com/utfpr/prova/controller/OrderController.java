package com.utfpr.prova.controller;

import com.utfpr.prova.model.Order;
import com.utfpr.prova.model.dto.OrderDTO;
import com.utfpr.prova.model.mapper.OrderMapper;
import com.utfpr.prova.model.service.OrderService;
import com.utfpr.prova.security.JwtUser;
import com.utfpr.prova.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordens")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> all(){

        List<Order> employees = this.orderService.findAll();

        ArrayList<OrderDTO> employeeDTOS = new ArrayList<>();

        for (Order o: employees) {
            employeeDTOS.add(orderMapper.toDTO(o));
        }

        return ResponseEntity.ok(employeeDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<OrderDTO>> getById(@PathVariable("id") Long id){
        Response<OrderDTO> response = new Response<>();

        Optional<Order> order = orderService.findById(id);

        if (!order.isPresent()){
            response.addError("Ordem não encontrada!");
            return ResponseEntity.badRequest().body(response);
        }

        OrderDTO orderDTO = orderMapper.toDTO(order.get());
        response.setData(orderDTO);
        return  ResponseEntity.ok(response);
    }


    @PostMapping("/novo")
    public ResponseEntity<Response<OrderDTO>> save(@Valid @RequestBody OrderDTO orderDTO, BindingResult result) {

        Response<OrderDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        Order order = orderMapper.toEntity(orderDTO);
        orderService.save(order);
        response.setData(orderDTO);
        return ResponseEntity.ok(response);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody OrderDTO dto, BindingResult result) {

        JwtUser currentUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Response<OrderDTO> response = new Response<>();
        if (result.hasErrors()) {
            response.setErrors(result);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Order> o = orderService.findById(id);
        if (!o.isPresent()) {
            response.addError("Ordem não encontrada.");
            return ResponseEntity.badRequest().body(response);
        }
        if (!currentUser.getId().equals(o.get().getEmployee().getUser().getId())) {
            response.addError("Você pode atualizar ordens criadas com seu próprio usuário.");
            return ResponseEntity.badRequest().body(response);
        }
        dto.setId(id);
        Order order = orderMapper.toEntity(dto);
        orderService.save(order);
        response.setData(dto);

        return ResponseEntity.ok(response);
    }
}
