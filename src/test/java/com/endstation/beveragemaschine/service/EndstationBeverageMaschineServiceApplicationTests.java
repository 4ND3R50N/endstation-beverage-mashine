package com.endstation.beveragemaschine.service;

import com.endstation.beveragemaschine.service.configuration.H2TestProfileJPAConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        EndstationBeverageMaschineServiceApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class EndstationBeverageMaschineServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
