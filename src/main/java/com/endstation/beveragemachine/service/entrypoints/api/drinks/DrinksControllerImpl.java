package com.endstation.beveragemachine.service.entrypoints.api.drinks;

import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.drinks.DrinkService;
import com.endstation.beveragemachine.service.core.usecase.ingredients.IngredientsService;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DrinksControllerImpl implements DrinksApiDelegate {

    private final DrinkService drinkService;
    private final IngredientsService ingredientsService;

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

    @Override
    public ResponseEntity<Void> deleteIngredient(Long ingredientId) {
        return ingredientsService.deleteIngredient(ingredientId);
    }

    @Override
    public ResponseEntity<DrinkDataResponse> createDrink(DrinkData drinkData) {
        return drinkService.createDrink(drinkData);
    }

    @Override
    public ResponseEntity<List<DrinkData>> getDrinks() {
        return drinkService.getDrinks();
    }

    @Override
    public ResponseEntity<DrinkData> getDrink(Long drinkId) {
        return drinkService.getDrinkById(drinkId);
    }

    @Override
    public ResponseEntity<Void> updateDrink(Long drinkId, DrinkData drinkData) {
        return drinkService.updateDrink(drinkId, drinkData);
    }

    @Override
    public ResponseEntity<Void> deleteDrink(Long drinkId) {
        return drinkService.deleteDrinkBy(drinkId);
    }
}
