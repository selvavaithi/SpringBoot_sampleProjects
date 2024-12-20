package com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomJsonProcess {

    public Map<String, Object> processNumbers(List<Double> numbers) {
        Map<String, Object> response = new HashMap<>();

        if (numbers == null || numbers.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Input JSON must contain a 'numbers' field with a non-empty array of integers");
            response.put("data", null);
            return response;
        }

        for(int i=0; i<numbers.size();i++){
            System.out.println("N["+i+"] = "+numbers.get(i));
        }

        // Perform calculations
        int sum = numbers.stream().mapToInt(Double::intValue).sum();
        double average = numbers.stream().mapToInt(Double::intValue).average().orElse(0.0);
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        // Build the response JSON
        Map<String, Object> data = new HashMap<>();
        data.put("sum", sum);
        data.put("average", average);
        data.put("sorted", sorted);
        data.put("original", numbers);

        response.put("status", "success");
        response.put("message", "Numbers processed successfully");
        response.put("data", data);

        return response;
    }

}
