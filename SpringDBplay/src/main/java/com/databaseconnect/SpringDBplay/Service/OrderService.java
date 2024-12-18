package com.databaseconnect.SpringDBplay.Service;

import com.databaseconnect.SpringDBplay.Bean.OrderDTO;
import com.databaseconnect.SpringDBplay.Entity.Order;
import com.databaseconnect.SpringDBplay.Entity.OrderStatus;
import com.databaseconnect.SpringDBplay.mapper.OrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public OrderDTO createOrder(OrderDTO order){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setProductName(order.getProductName());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(String.valueOf(order.getStatus()));

//        System.out.println("RESPONSE:: "+orderDTO.toString());

        return orderDTO;
    }

    public List<OrderDTO> createMultipleOrdersAndReturnDtos() {
        List<OrderDTO> orderDtoList = new ArrayList<>();
        String[] customers = {"John Doe", "Jane Smith", "Alice Johnson", "Bob Brown"};
        String[] products = {"Wireless Headphones", "Smartphone", "Laptop", "Tablet"};
        int[] quantities = {2, 1, 1, 3};
        double[] prices = {150.00, 700.00, 1200.00, 300.00};

        for (int i = 0; i < 4; i++) {
            Order order = new Order();
            order.setId((long) (i + 1)); // Auto-generated in real scenarios
            order.setCustomerName(customers[i]);
            order.setProductName(products[i]);
            order.setQuantity(quantities[i]);
            order.setPrice(prices[i]);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.PENDING);

            // Convert Order entity to DTO and add to list
            OrderDTO orderDto = OrderMapper.toDto(order);
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

}
