package ch.mapirium.server.fielddefinitionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FieldDefinitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FieldDefinitionServiceApplication.class, args);
	}
}
