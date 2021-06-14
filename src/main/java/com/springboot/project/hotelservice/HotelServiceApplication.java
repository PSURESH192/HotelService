package com.springboot.project.hotelservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages ="com.springboot.project.hotelservice")
public class HotelServiceApplication {

	static final Logger logger  = LogManager.getLogger(HotelServiceApplication.class.getName());

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.project.hotelservice")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelServiceApplication.class, args);
	}

}
