package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientsService {

    private final IngredientMapper ingredientMapper;
    private final IngredientsRepository IngredientsRepository;

    public ResponseEntity<IngredientResponse> createIngredient(IngredientData ingredientData) {

        if (IngredientsRepository.existsByName(ingredientData.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(IngredientResponse.builder()
                .ingredientId(IngredientsRepository.save(ingredientMapper
                        .map(ingredientData))
                        .getIngredientId())
                .build(), HttpStatus.CREATED);
    }
    public ResponseEntity<List<IngredientData>> getIngredients() {
        return ResponseEntity.ok(IngredientsRepository.findAll().stream()
                .map(ingredientMapper::map)
                .collect(Collectors.toList()));
    }
    public ResponseEntity<IngredientData> getIngredient(Long ingredientId) {
        Optional<IngredientData> ingredientDataOptional = IngredientsRepository.findById(ingredientId)
                .map(ingredientMapper::map);
        return ingredientDataOptional.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Void> updateIngredient(Long ingredientId, IngredientData ingredientData) {
        // isEmpty() is sadly currently not working in native images due the following error:
        // java.lang.NoSuchMethodError: java.util.Optional.isEmpty()
        if (!IngredientsRepository.findById(ingredientId).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        IngredientsRepository.save(ingredientMapper.map(ingredientId, ingredientData));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Void> deleteIngredient(Long ingredientId) {
        IngredientsRepository.findById(ingredientId).ifPresent(IngredientsRepository::delete);

        return ResponseEntity.ok().build();
    }
}
