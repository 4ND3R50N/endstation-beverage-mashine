package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record IngredientsService(IngredientsRepository ingredientsRepository,
                                 IngredientMapper ingredientMapper) {
    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {

        if (ingredientsRepository.existsByName(ingredientData.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(IngredientResponse.builder()
                .ingredientId(ingredientsRepository.save(ingredientMapper
                        .map(ingredientData))
                        .getIngredientId())
                .build(), HttpStatus.CREATED);
    }
    public ResponseEntity<List<IngredientData>> getIngredients() {
        return ResponseEntity.ok(ingredientsRepository.findAll().stream()
                .map(ingredientMapper::map)
                .toList());
    }

    public ResponseEntity<IngredientData> getIngredient(Long ingredientId) {
        Optional<IngredientData> ingredientDataOptional = ingredientsRepository.findById(ingredientId)
                .map(ingredientMapper::map);
        return ingredientDataOptional.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Void> updateIngredient(Long ingredientId, IngredientData ingredientData) {

        if (ingredientsRepository.findById(ingredientId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredientsRepository.save(ingredientMapper.map(ingredientId, ingredientData));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
