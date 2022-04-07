package br.com.jonatha.consumo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConsumoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumoApiApplication.class, args);
	}

}
