package com.utfpr.prova.model.mapper;

import com.utfpr.prova.model.Order;
import com.utfpr.prova.model.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private ModelMapper mapper;

    public Order toEntity(OrderDTO dto){
        Order order = mapper.map(dto, Order.class);
        return order;
    }

    public OrderDTO toDTO(Order entity){
        OrderDTO orderDTO = mapper.map(entity, OrderDTO.class);
        return orderDTO;
    }
}
