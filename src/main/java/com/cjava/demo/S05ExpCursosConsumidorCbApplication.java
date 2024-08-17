package com.cjava.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient // Habilita el cliente de descubrimiento
@EnableFeignClients // Habilita el cliente Feign
@SpringBootApplication
public class S05ExpCursosConsumidorCbApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05ExpCursosConsumidorCbApplication.class, args);
	}

}
