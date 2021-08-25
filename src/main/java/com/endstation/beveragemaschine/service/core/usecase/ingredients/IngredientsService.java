package com.endstation.beveragemaschine.service.core.usecase.ingredients;

import com.endstation.beveragemaschine.service.model.IngredientData;
import com.endstation.beveragemaschine.service.model.IngredientResponse;
import com.endstation.beveragemaschine.service.dataprovider.db.ingredients.IngredientsEntity;
import com.endstation.beveragemaschine.service.dataprovider.db.ingredients.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientResponse createIngredient(IngredientData ingredientData) {

        IngredientsEntity save = ingredientsRepository.save(IngredientsEntity.builder()
                .name(ingredientData.getName())
                .liquidType(ingredientData.getLiquidType())
                .build());
        Long ingredientId = save.getIngredientId();

        return IngredientResponse.builder()
                .ingredientId(ingredientId)
                .build();
    }
}
