package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkIngredientConceptionEntity;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.model.DrinkData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrinkMapper {

    @PersistenceContext
    private final EntityManager entityManager;

    public DrinkEntity map(DrinkData drinkData) {
        return DrinkEntity.builder()
                .isBasicDrink(drinkData.getIsBasicDrink())
                .name(drinkData.getName())
                .visitorId(drinkData.getVisitorId())
                .ingredientConceptions(drinkData.getIngredients().stream()
                        .map(drinkIngredient -> DrinkIngredientConceptionEntity.builder()
                                .ingredient(entityManager.find(IngredientEntity.class, drinkIngredient.getIngredientId()))
                                .amount(drinkIngredient.getAmount().intValue())
                                .quantityType(drinkIngredient.getQuantityType())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
