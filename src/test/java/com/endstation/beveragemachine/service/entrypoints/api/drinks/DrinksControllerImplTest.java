package com.endstation.beveragemachine.service.entrypoints.api.drinks;

import com.endstation.beveragemachine.service.core.usecase.drinks.DrinkService;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import com.endstation.beveragemachine.service.model.IngredientData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DrinksControllerImplTest {

    @Mock
    IngredientData ingredientData = mock(IngredientData.class);

    @Mock
    private final DrinkService drinkService = mock(DrinkService.class);

    @Mock
    private final IngredientsService ingredientsService = mock(IngredientsService.class);

    DrinksControllerImpl ingredientsController = new DrinksControllerImpl(drinkService, ingredientsService);

    @Test
    void shouldExecuteCreateIngredientServiceFunction() {
        // when
        ingredientsController.createIngredient(ingredientData);

        // verify
        verify(ingredientsService, times(1)).createIngredient(ingredientData);
    }

    @Test
    void shouldExecuteGetIngredientServiceFunction() {
        // when
        ingredientsController.getIngredients();

        // verify
        verify(ingredientsService, times(1)).getIngredients();
    }

    @Test
    void shouldExecuteGetIngredientByIdServiceFunction() {
        // when
        ingredientsController.getIngredient(12L);

        // verify
        verify(ingredientsService, times(1)).getIngredient(12L);
    }

    @Test
    void shouldExecuteUpdateIngredientServiceFunction() {

        IngredientData ingredientData = mock(IngredientData.class);

        // when
        ingredientsController.updateIngredient(12L, ingredientData);

        // verify
        verify(ingredientsService, times(1)).updateIngredient(12L, ingredientData);
    }
}