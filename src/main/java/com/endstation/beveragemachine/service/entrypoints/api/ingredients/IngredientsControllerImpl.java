package com.endstation.beveragemachine.service.entrypoints.api.ingredients;

import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientsControllerImpl implements DrinksApiDelegate {

    private final IngredientsService ingredientsService;

    @Override
    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {
        return ingredientsService.createIngredient(ingredientData);
    }
}