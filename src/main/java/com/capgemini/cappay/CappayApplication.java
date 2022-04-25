package com.capgemini.cappay;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "CapPay", version = "1.0", description = "CapPay Digital Bank"),
		servers = {
				@Server(url = "http://localhost:8080")
		}
)
public class CappayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CappayApplication.class, args);
	}

}
