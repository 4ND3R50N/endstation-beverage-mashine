package com.endstation.beveragemachine.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EndstationBeverageMachineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndstationBeverageMachineServiceApplication.class, args);
    }

}
