package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkMapper drinkMapper;
    private final DrinkRepository drinkRepository;

    public ResponseEntity<DrinkDataResponse> createDrink(DrinkData drinkData) {
        Long drinkId = drinkRepository.save(drinkMapper.map(drinkData)).getDrinkId();
        return new ResponseEntity<>(DrinkDataResponse.builder()
                .drinkId(drinkId)
                .build(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<DrinkData>> getDrinks() {
        return ResponseEntity.ok()
                .body(drinkRepository.findAll().stream()
                        .map(drinkMapper::map)
                        .collect(Collectors.toList()));
    }

    public ResponseEntity<DrinkData> getDrinkById(Long drinkId) {
        Optional<DrinkData> repositoryDrinkDataOptional = drinkRepository.findById(drinkId)
                .map(drinkMapper::map);
        return repositoryDrinkDataOptional.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
