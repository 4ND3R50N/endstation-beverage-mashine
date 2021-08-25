package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsEntity;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record IngredientsService(
        IngredientsRepository ingredientsRepository) {

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

    public ResponseEntity<List<IngredientData>> getIngredients() {
        return ResponseEntity.ok(ingredientsRepository.findAll().stream()
                .map(ingredientsEntity -> IngredientData.builder()
                        .name(ingredientsEntity.getName())
                        .liquidType(ingredientsEntity.getLiquidType())
                        .build())
                .toList());
    }
}
