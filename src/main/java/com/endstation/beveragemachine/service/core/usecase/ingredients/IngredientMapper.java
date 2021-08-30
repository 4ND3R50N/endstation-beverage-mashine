package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.model.IngredientData;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientData map(IngredientEntity ingredientEntity) {
        return IngredientData.builder()
                .ingredientId(JsonNullable.of(ingredientEntity.getIngredientId()))
                .name(ingredientEntity.getName())
                .liquidType(ingredientEntity.getLiquidType())
                .build();
    }

    public IngredientEntity map(Long ingredientId, IngredientData ingredientData) {
        return IngredientEntity.builder()
                .ingredientId(ingredientId)
                .name(ingredientData.getName())
                .liquidType(ingredientData.getLiquidType())
                //.drinkConceptions(List.of())
                .build();
    }

    public IngredientEntity map(IngredientData ingredientData) {
        return IngredientEntity.builder()
                .name(ingredientData.getName())
                .liquidType(ingredientData.getLiquidType())
                //.drinkConceptions(List.of())
                .build();
    }
}
