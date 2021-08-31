package com.endstation.beveragemachine.service.entrypoints.api.drinks;

import com.endstation.beveragemachine.service.core.usecase.drinks.DrinkService;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import com.endstation.beveragemachine.service.model.DrinkData;
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
    void shouldExecuteDeleteIngredientByIdServiceFunction() {
        // when
        ingredientsController.deleteIngredient(12L);

        // verify
        verify(ingredientsService, times(1)).deleteIngredient(12L);
    }

    @Test
    void shouldExecuteUpdateIngredientServiceFunction() {

        IngredientData ingredientData = mock(IngredientData.class);

        // when
        ingredientsController.updateIngredient(12L, ingredientData);

        // verify
        verify(ingredientsService, times(1)).updateIngredient(12L, ingredientData);
    }

    @Test
    void shouldExecuteCreateDrinkServiceFunction() {

        DrinkData drinkData = mock(DrinkData.class);

        // when
        ingredientsController.createDrink(drinkData);

        // verify
        verify(drinkService, times(1)).createDrink(drinkData);
    }

    @Test
    void shouldExecuteGetDrinksServiceFunction() {

        // when
        ingredientsController.getDrinks();

        // verify
        verify(drinkService, times(1)).getDrinks();
    }

    @Test
    void shouldExecuteGetDrinkByIdServiceFunction() {

        Long id = 12L;

        // when
        ingredientsController.getDrink(id);

        // verify
        verify(drinkService, times(1)).getDrinkById(id);
    }
}