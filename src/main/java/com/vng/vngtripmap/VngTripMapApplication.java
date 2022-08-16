package com.vng.vngtripmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VngTripMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(VngTripMapApplication.class, args);
    }

}
