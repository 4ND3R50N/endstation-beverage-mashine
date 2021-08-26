package com.endstation.beveragemachine.service.entrypoints.api.ingredients;

import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record IngredientsControllerImpl(IngredientsService ingredientsService) implements DrinksApiDelegate {
    @Override
    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {
        return ingredientsService.createIngredient(ingredientData);
    }

    @Override
    public ResponseEntity<List<IngredientData>> getIngredients() {
        return ingredientsService.getIngredients();
    }

    @Override
    public ResponseEntity<IngredientData> getIngredient(Long ingredientId) {
        return ingredientsService.getIngredient(ingredientId);
    }

    @Override
    public ResponseEntity<Void> updateIngredient(Long ingredientId, IngredientData ingredientData) {
        return ingredientsService.updateIngredient(ingredientId, ingredientData);
    }
}
