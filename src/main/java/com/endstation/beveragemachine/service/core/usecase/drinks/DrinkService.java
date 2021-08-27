package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkIngredientConceptionEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final EntityManager entityManager;
    private final DrinkRepository drinkRepository;

    public ResponseEntity<DrinkDataResponse> createDrink(DrinkData drinkData) {
        Long drinkId = drinkRepository.save(DrinkEntity.builder()
                .isBasicDrink(drinkData.getIsBasicDrink())
                .name(drinkData.getName())
                .visitorId(drinkData.getVisitorId())
                .ingredientConceptions(drinkData.getIngredients().stream()
                        .map(drinkIngredients -> DrinkIngredientConceptionEntity.builder()
                                .ingredient(entityManager.getReference(IngredientEntity.class, drinkIngredients.getIngredientId()))
                                .amount(drinkIngredients.getAmount().intValue())
                                .unit(drinkIngredients.getUnit())
                                .build())
                        .collect(Collectors.toList()))
                .build()).getDrinkId();
        return new ResponseEntity<>(DrinkDataResponse.builder()
                .drinkId(drinkId)
                .build(), HttpStatus.CREATED);
    }
}
