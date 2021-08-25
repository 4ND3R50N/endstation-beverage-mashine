package com.endstation.beveragemaschine.service.core.usecase.ingredients;

import com.endstation.beveragemaschine.service.model.IngredientData;
import com.endstation.beveragemaschine.service.model.IngredientResponse;
import com.endstation.beveragemaschine.service.dataprovider.db.ingredients.IngredientsEntity;
import com.endstation.beveragemaschine.service.dataprovider.db.ingredients.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {

        if (ingredientsRepository.existsByName(ingredientData.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(IngredientResponse.builder()
                .ingredientId(ingredientsRepository.save(IngredientsEntity.builder()
                        .name(ingredientData.getName())
                        .liquidType(ingredientData.getLiquidType())
                        .build()).getIngredientId())
                .build(), HttpStatus.CREATED);
    }
}
