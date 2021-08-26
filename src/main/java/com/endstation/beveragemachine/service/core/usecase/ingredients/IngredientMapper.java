package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsEntity;
import com.endstation.beveragemachine.service.model.IngredientData;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientData map(IngredientsEntity ingredientsEntity) {
        return IngredientData.builder()
                .name(ingredientsEntity.getName())
                .liquidType(ingredientsEntity.getLiquidType())
                .build();
    }

    public IngredientsEntity map(Long ingredientId, IngredientData ingredientData) {
        return IngredientsEntity.builder()
                .ingredientId(ingredientId)
                .name(ingredientData.getName())
                .liquidType(ingredientData.getLiquidType())
                .build();
    }

    public IngredientsEntity map(IngredientData ingredientData) {
        return IngredientsEntity.builder()
                .name(ingredientData.getName())
                .liquidType(ingredientData.getLiquidType())
                .build();
    }
}
