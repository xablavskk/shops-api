package com.xvlkk.sp.shops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ShopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopsApplication.class, args);
	}

}
