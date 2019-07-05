package com.utfpr.prova.model.service;

import com.utfpr.prova.model.Order;
import com.utfpr.prova.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public void deleteById(Long id){
        orderRepository.deleteById(id);
    }

}
