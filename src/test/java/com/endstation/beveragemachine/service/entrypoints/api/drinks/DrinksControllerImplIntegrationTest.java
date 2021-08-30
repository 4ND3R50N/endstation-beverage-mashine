package com.endstation.beveragemachine.service.entrypoints.api.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import com.endstation.beveragemachine.service.model.DrinkIngredient;
import com.endstation.beveragemachine.service.model.DrinkIngredient.UnitEnum;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientData.LiquidTypeEnum;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DrinksControllerImplIntegrationTest {

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateIngredientsAndADrink() {
        // given
        IngredientData orangeJuice = IngredientData.builder()
                .name("Orange Juice")
                .liquidType(LiquidTypeEnum.SOFTDRINK)
                .build();
        IngredientData vodka = IngredientData.builder()
                .name("Vodka")
                .liquidType(LiquidTypeEnum.ALCOHOL)
                .build();
        // when
        ResponseEntity<IngredientResponse> resultOne = testRestTemplate.exchange("/api/v1//drinks/ingredients", HttpMethod.POST, new HttpEntity<>(orangeJuice), IngredientResponse.class);
        ResponseEntity<IngredientResponse> resultTwo = testRestTemplate.exchange("/api/v1//drinks/ingredients", HttpMethod.POST, new HttpEntity<>(vodka), IngredientResponse.class);

        assertEquals(resultOne.getStatusCode(), HttpStatus.CREATED);
        assertEquals(resultTwo.getStatusCode(), HttpStatus.CREATED);

        Long orangeJuiceId = Objects.requireNonNull(resultOne.getBody()).getIngredientId();
        Long vodkaId = Objects.requireNonNull(resultTwo.getBody()).getIngredientId();

        DrinkData drink = DrinkData.builder()
                .name("Vodka-O")
                .visitorId("Lars P.")
                .isBasicDrink(true)
                .ingredients(List.of(DrinkIngredient.builder()
                                .ingredientId(vodkaId)
                                .amount(BigDecimal.valueOf(1))
                                .unit(UnitEnum.CL)
                                .build(),
                        DrinkIngredient.builder()
                                .ingredientId(orangeJuiceId)
                                .amount(BigDecimal.valueOf(2))
                                .unit(UnitEnum.CL)
                                .build()))
                .build();

        ResponseEntity<DrinkDataResponse> resultThree = testRestTemplate.exchange("/api/v1/drinks", HttpMethod.POST, new HttpEntity<>(drink), DrinkDataResponse.class);
        assertEquals(resultThree.getStatusCode(), HttpStatus.CREATED);

    }
}