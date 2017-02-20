package com.emc.it.ooa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.emc.it.ooa.sample.sqlinj.SQLFilter;
import com.emc.it.ooa.sample.xss.XSSFilterNew;

@SpringBootApplication
public class Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
