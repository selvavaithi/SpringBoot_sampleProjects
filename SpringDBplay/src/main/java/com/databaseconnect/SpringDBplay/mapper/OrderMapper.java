package com.databaseconnect.SpringDBplay.mapper;

import com.databaseconnect.SpringDBplay.Bean.OrderDTO;
import com.databaseconnect.SpringDBplay.Entity.Order;
import com.databaseconnect.SpringDBplay.Entity.OrderStatus;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomerName(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getOrderDate(),
                order.getStatus().name() // Convert enum to string
        );
    }

    public static Order toEntity(OrderDTO orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerName(orderDto.getCustomerName());
        order.setProductName(orderDto.getProductName());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setOrderDate(orderDto.getOrderDate());
        order.setStatus(OrderStatus.valueOf(orderDto.getStatus())); // Convert string to enum
        return order;
    }
}
