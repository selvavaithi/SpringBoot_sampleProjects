package com.databaseconnect.SpringDBplay.Controller;

import com.databaseconnect.SpringDBplay.Bean.OrderDTO;
import com.databaseconnect.SpringDBplay.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderTypeController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrders(){
        return ResponseEntity.ok(orderService.createMultipleOrdersAndReturnDtos());
    }

   // @PostMapping("/order/create")
    @RequestMapping(value = "/order/create", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        System.out.println("Entering the process");

        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);

    }
}
