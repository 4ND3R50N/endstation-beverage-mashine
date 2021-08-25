package com.endstation.beveragemachine.service;

import com.endstation.beveragemachine.service.configuration.H2TestProfileJPAConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        EndstationBeverageMachineServiceApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class EndstationBeverageMachineServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
