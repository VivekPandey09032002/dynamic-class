package com.nucleus.generator;

import com.nucleus.generator.service.GenerateDynamicClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class GeneratorApplication {

	public static void main(String[] args) throws IOException {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(GeneratorApplication.class, args);

		GenerateDynamicClass generateDynamicClass = applicationContext.getBean(GenerateDynamicClass.class);
		generateDynamicClass.writeToPackage();
	}

}
