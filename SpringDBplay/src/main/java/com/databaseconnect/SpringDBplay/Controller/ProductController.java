package com.databaseconnect.SpringDBplay.Controller;

import com.databaseconnect.SpringDBplay.Entity.Product;
import com.databaseconnect.SpringDBplay.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductsRepository productRepository;

    @GetMapping("/products")
    public List<String> getAllProductNames() {
        return productRepository.findAll()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/getItems")
    public ResponseEntity<Object> getItems(){
        List<Product> items = productRepository.findAll();
        return generateResponse("Complete Data!", HttpStatus.OK, items);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }
}

