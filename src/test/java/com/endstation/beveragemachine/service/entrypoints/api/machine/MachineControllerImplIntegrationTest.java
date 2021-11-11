package com.endstation.beveragemachine.service.entrypoints.api.machine;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkIngredientConceptionRepository;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import com.endstation.beveragemachine.service.model.BottleSlots;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MachineControllerImplIntegrationTest {

    @Autowired
    DrinkIngredientConceptionRepository drinkIngredientConceptionRepository;

    @Autowired
    IngredientsRepository ingredientsRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    private Long vodkaId;
    private Long orangeJuiceId;

    @BeforeEach
    public void setup() {
        drinkRepository.deleteAll();
        drinkIngredientConceptionRepository.deleteAll();
        ingredientsRepository.deleteAll();
    }

    @Test
    public void shouldCreateIngredientsAndAssignThemToSlots() {
        // given
        createIngredients();

        BottleSlots bottleSlotOne = BottleSlots.builder()
                .slotId(1L)
                .ingredientId(vodkaId)
                .build();

        BottleSlots bottleSlotTwo = BottleSlots.builder()
                .slotId(2L)
                .ingredientId(orangeJuiceId)
                .build();

        // when
        ResponseEntity<List<BottleSlots>> resultOne = testRestTemplate.exchange("/api/v1/beverage-machine/ingredients", HttpMethod.POST, new HttpEntity<>(bottleSlotOne), new ParameterizedTypeReference<>(){});
        ResponseEntity<List<BottleSlots>> resultTwo = testRestTemplate.exchange("/api/v1/beverage-machine/ingredients", HttpMethod.POST, new HttpEntity<>(bottleSlotTwo), new ParameterizedTypeReference<>(){});

        assertEquals(HttpStatus.CREATED, resultOne.getStatusCode());
        assertEquals(HttpStatus.CREATED, resultTwo.getStatusCode());

        assertEquals(1, Objects.requireNonNull(resultOne.getBody()).size());
        assertEquals(2, Objects.requireNonNull(resultTwo.getBody()).size());

    }


    public void createIngredients() {
        // given
        IngredientData orangeJuice = IngredientData.builder()
                .name("Orange Juice")
                .liquidType(IngredientData.LiquidTypeEnum.SOFTDRINK)
                .build();
        IngredientData vodka = IngredientData.builder()
                .name("Vodka")
                .liquidType(IngredientData.LiquidTypeEnum.ALCOHOL)
                .build();
        // when
        // create two Ingredients
        ResponseEntity<IngredientResponse> resultOne = testRestTemplate.exchange("/api/v1/drinks/ingredient", HttpMethod.POST, new HttpEntity<>(orangeJuice), IngredientResponse.class);
        ResponseEntity<IngredientResponse> resultTwo = testRestTemplate.exchange("/api/v1/drinks/ingredient", HttpMethod.POST, new HttpEntity<>(vodka), IngredientResponse.class);

        assertEquals(resultOne.getStatusCode(), HttpStatus.CREATED);
        assertEquals(resultTwo.getStatusCode(), HttpStatus.CREATED);

        orangeJuiceId = Objects.requireNonNull(resultOne.getBody()).getIngredientId();
        vodkaId = Objects.requireNonNull(resultTwo.getBody()).getIngredientId();

        // check database
        assertEquals(2, (long) ingredientsRepository.findAll().size());
        assertEquals(0, (long) drinkIngredientConceptionRepository.findAll().size());
        assertEquals(0, (long) drinkRepository.findAll().size());
    }

}
