package com.springbackgroundfunctionmain.SpringBackgroundFunctionMain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans.CustomJsonProcess;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	public Function<String, String> pubSubFunction() {
		System.out.println("This is entering*/*/*/*");
		return (reader) -> reader.lines().collect(Collectors.joining());
	}

//	@Bean
//	public Consumer<PubSubMessage> pubSubFunction() {
//		return message -> {
//			// The PubSubMessage data field arrives as a base-64 encoded string and must be decoded.
//			// See: https://cloud.google.com/functions/docs/calling/pubsub#event_structure
//			String decodedMessage = new String(Base64.getDecoder().decode(message.getData()), StandardCharsets.UTF_8);
//			System.out.println("Received Pub/Sub message with data: " + decodedMessage);
//		};
//	}

//	@Bean
//	public Consumer<PubSubMessage> processJson() {
//		return input -> {
//
//			// Create ObjectMapper instance
//			ObjectMapper objectMapper = new ObjectMapper();
//
//			// Parse JSON string into a Map
//			Map<String, List<Double>> map = null;
//			try {
//				map = objectMapper.readValue(input.getAttributes().get("numbers"), Map.class);
//			} catch (JsonProcessingException e) {
//				throw new RuntimeException(e);
//			}
//
//			// Extract the "numbers" field as a List<Double>
//			List<Double> numbers = map.get("numbers");
//
////			List<Double> numbers = (List<Double>) input.getAttributes().get("numbers");
//
//			Map<String, Object> getReturn = customJsonProcess.processNumbers(numbers);
//
//			System.out.println("*/*/*/*/-*-*-*-*-*-*-*-*-*-*-*-");
//
//			System.out.println(getReturn.toString());
//
//			System.out.println("*8*8*8*8*8*8*8*8*8*8*8*8*8*8*8*8*");
//
//
//		};
//	}

//	@Bean
//	public Function<Request, Map<String, Object>> processJson() {
//		return message -> {
//			try {
//				// Decode Base64 data
////				String decodedData = new String(Base64.getDecoder().decode(message.getData()));
//
//				// Process the decoded data (assume it contains JSON with numbers)
////				List<Integer> numbers = List.of(4, 2, 7, 1, 5); // Replace this with your JSON parsing logic
//
//				Map<String, Object> response = Map.of(
//						"status", "success",
//						"message", "Message processed successfully",
//						"data", message.getContext().toString()
//				);
//
//				System.out.println("/////////////*****************//////////////");
//				System.out.println(response.toString());
//				return response;
//
//			} catch (Exception e) {
//				return Map.of(
//						"status", "error",
//						"message", "Failed to process message: " + e.getMessage(),
//						"data", null
//				);
//			}
//		};
//	}

}
