package com.scottlogic.javatraining;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JavaTrainingApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JavaTrainingApplication.class).run(args);
	}
}
