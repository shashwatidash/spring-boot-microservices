package com.example.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts Microservice REST API Documentation", 
        version = "1.0", 
        description = "REST API for Accounts Microservice",
        contact = @Contact(name = "Shaswati Dash", email = "shashwati.dash@gmail.com"),
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
    ),
    externalDocs = @ExternalDocumentation(
        description = "Accounts Microservice Documentation", 
        url = "https://springdoc.org/"
    )
)
public class MicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}
