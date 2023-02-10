package com.happy3friends.eatcleanmenubackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.happy3friends.eatcleanmenubackend.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("EAT CLEAN MENU API")
                .description("Eat Clean Menu API")
                .contact(new Contact("Tien Huynh", "https://github.com/tienhuynh-tn", "tien.huynhlt.tn@gmail.com"))
                .license("The GNU General Public License v3.0")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
                .version("1.0.0")
                .build();
    }
}