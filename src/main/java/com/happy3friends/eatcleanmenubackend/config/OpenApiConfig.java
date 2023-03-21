package com.happy3friends.eatcleanmenubackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String securitySchemeName = "Bearer Authentication";

    @Bean
    public OpenAPI toiletMapOpenApi() {
        Server productionServer1 = new Server();
        productionServer1.setUrl("https://toilet-map.azurewebsites.net/");
        productionServer1.setDescription("Server URL in Production environment 1");

        Server productionServer2 = new Server();
        productionServer2.setUrl("https://toiletmap.azurewebsites.net/");
        productionServer2.setDescription("Server URL in Production environment 2");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8081");
        localServer.setDescription("Server URL in Local environment");

        return new OpenAPI()
                .info(new Info().title("TOILET MAP API")
                        .description("Toilet Map API")
                        .contact(new Contact()
                                .email("tien.huynhlt.tn@gmail.com")
                                .name("Tien Huynh TN")
                                .url("https://github.com/tienhuynh-tn"))
                        .license(new License()
                                .name("The GNU General Public License v3.0")
                                .url("https://www.gnu.org/licenses/gpl-3.0.html"))
                        .version("1.0.0"))
                .servers(List.of(productionServer1, productionServer2, localServer))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .description("JWT Authorization header using the Bearer scheme. Enter your token in the text input below. Example: '12345abcdef' (Not include keyword 'Bearer')")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)));
    }
}
