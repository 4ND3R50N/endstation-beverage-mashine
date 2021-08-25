package com.endstation.beveragemaschine.service.entrypoints.api.ingredients;

import com.endstation.beveragemaschine.service.core.usecase.ingredients.IngredientsService;
import com.endstation.beveragemaschine.service.model.IngredientData;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IngredientsControllerImplTest {

    @Mock
    IngredientData ingredientData = mock(IngredientData.class);

    @Mock
    private final IngredientsService ingredientsService = mock(IngredientsService.class);

    IngredientsControllerImpl ingredientsController = new IngredientsControllerImpl(ingredientsService);
    @Test
    void shouldExecuteCreateIngredientServiceFunction() {

        // when
        ingredientsController.createIngredient(ingredientData);

        // verify
        verify(ingredientsService, times(1)).createIngredient(ingredientData);
    }
}