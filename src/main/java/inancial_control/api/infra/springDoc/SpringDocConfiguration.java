package inancial_control.api.infra.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Financial control")
                        .description(description)
                        .contact(new Contact()
                                .name("Time Backend - Matheus Mozart da Silva Neves Borges")
                                .email("mmsnborges@gmail.com;bmozart.dev@gmail.com"))
                        .license(new License()
                                .name("CC BY-NC")
                                .url("somente em local host")));
    }

    String description = """
            API Rest da aplicação financial-control, contendo as funcionalidades de CRUD  de usuários e de 
            transações, além de manipulação de dados com streams, desing patterns em MVC, utilizando spring MVC, 
            github: https://github.com/Mtmozart/financial-controll.
            """;
}
