package com.springbackgroundfunctionmain.SpringBackgroundFunctionMain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans.CustomJsonProcess;
import com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans.PubSubMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class SpringBackgroundFunctionMainApplication {

	@Autowired
	private final CustomJsonProcess customJsonProcess;

	// Constructor-based dependency injection
	public SpringBackgroundFunctionMainApplication(CustomJsonProcess customJsonProcess) {
		this.customJsonProcess = customJsonProcess;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBackgroundFunctionMainApplication.class, args);
	}

	@Bean
	public Consumer<PubSubMessage> processJson() {
		return input -> {

			// Create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();

			// Parse JSON string into a Map
			Map<String, List<Double>> map = null;
			try {
				map = objectMapper.readValue(input.getAttributes().get("numbers"), Map.class);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}

			// Extract the "numbers" field as a List<Double>
			List<Double> numbers = map.get("numbers");

//			List<Double> numbers = (List<Double>) input.getAttributes().get("numbers");

			Map<String, Object> getReturn = customJsonProcess.processNumbers(numbers);
		};
	}

}
