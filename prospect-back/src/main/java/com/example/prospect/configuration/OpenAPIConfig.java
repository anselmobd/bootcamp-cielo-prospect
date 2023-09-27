package com.example.prospect.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("URL do serviço em ambiente de desenvolvimento");

        Contact contact = new Contact();
        contact.setEmail("anselmo.blanco.dominguez@gmail.com");
        contact.setName("Anselmo Blanco Dominguez");
        contact.setUrl("https://github.com/anselmobd");

        Info info = new Info()
                .title("API do desafio individual do Bootcamp Cielo Dev - AdaTech")
                .version("1.0")
                .contact(contact)
                .description("CRUD de Pessoa física e Pessoa jurídica para prospecção de clientes pela Cielo");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
