package com.ClubAccount_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClubAccountBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClubAccountBeApplication.class, args);
    }
}
