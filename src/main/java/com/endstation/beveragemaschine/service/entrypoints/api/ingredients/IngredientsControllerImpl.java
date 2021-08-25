package com.endstation.beveragemaschine.service.entrypoints.api.ingredients;

import com.endstation.beveragemaschine.service.api.DrinksApiDelegate;
import com.endstation.beveragemaschine.service.model.IngredientData;
import com.endstation.beveragemaschine.service.model.IngredientResponse;
import com.endstation.beveragemaschine.service.core.usecase.ingredients.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientsControllerImpl implements DrinksApiDelegate {

    private final IngredientsService ingredientsService;

    @Override
    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {
        return new ResponseEntity<>(ingredientsService.createIngredient(ingredientData), HttpStatus.CREATED);
    }
}
