package br.com.pagamentos.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicediscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicediscoveryApplication.class, args);
	}

}

// service discovery (Eureka service) - catálago com todos os serviços que podem atenter uma requisição