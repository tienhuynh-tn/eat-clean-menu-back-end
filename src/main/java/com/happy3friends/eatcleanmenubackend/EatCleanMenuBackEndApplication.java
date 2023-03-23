package com.happy3friends.eatcleanmenubackend;

//import com.happy3friends.eatcleanmenubackend.config.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties(AppProperties.class)
public class EatCleanMenuBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatCleanMenuBackEndApplication.class, args);
    }

}