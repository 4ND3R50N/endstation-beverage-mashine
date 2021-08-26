package com.endstation.beveragemachine.service.entrypoints.api.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IngredientsControllerImplTest {

    @Mock
    IngredientData ingredientData = mock(IngredientData.class);


    @Mock
    private IngredientsService ingredientsService = mock(IngredientsService.class);

    IngredientsControllerImpl ingredientsController = new IngredientsControllerImpl(ingredientsService);

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
}