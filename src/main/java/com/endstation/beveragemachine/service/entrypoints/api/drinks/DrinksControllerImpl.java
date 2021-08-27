package com.endstation.beveragemachine.service.entrypoints.api.drinks;

import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.drinks.DrinkService;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DrinksControllerImpl implements DrinksApiDelegate {

    private final DrinkService drinkService;

    @Override
    public ResponseEntity<DrinkDataResponse> createDrink(DrinkData drinkData) {
        return drinkService.createDrink(drinkData);
    }

    @Override
    public ResponseEntity<List<DrinkData>> getDrinks() {
        return null;
    }

    @Override
    public ResponseEntity<DrinkData> getDrink(Long drinkId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateDrink(Long ingredientId, DrinkData drinkData) {
        return null;
    }
}
