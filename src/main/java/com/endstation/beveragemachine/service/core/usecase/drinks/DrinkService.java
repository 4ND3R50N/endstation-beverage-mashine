package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
