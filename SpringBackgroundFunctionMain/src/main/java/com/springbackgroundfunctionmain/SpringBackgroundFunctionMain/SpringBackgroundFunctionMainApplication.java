package com.springbackgroundfunctionmain.SpringBackgroundFunctionMain;

import com.springbackgroundfunctionmain.SpringBackgroundFunctionMain.Beans.CustomJsonProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
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
	public Function<Map<String, Object>, Map<String, Object>> processJson() {
		return input -> {
			List<Double> numbers = (List<Double>) input.get("numbers");

			//Message<String> message = MessageBuilder.withPayload(payload).setHeader(FunctionInvoker.HTTP_STATUS_CODE, 404).build();

			return customJsonProcess.processNumbers(numbers);
		};
	}

}
