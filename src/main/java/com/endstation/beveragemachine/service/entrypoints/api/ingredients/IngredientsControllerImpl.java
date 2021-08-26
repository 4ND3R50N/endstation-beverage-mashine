package com.endstation.beveragemachine.service.entrypoints.api.ingredients;

import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record IngredientsControllerImpl(
        IngredientsService ingredientsService) implements DrinksApiDelegate {

    @Override
    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {
        return ingredientsService.createIngredient(ingredientData);
    }

    @Override
    public ResponseEntity<List<IngredientData>> getIngredients() {
        return ingredientsService.getIngredients();
    }
}
