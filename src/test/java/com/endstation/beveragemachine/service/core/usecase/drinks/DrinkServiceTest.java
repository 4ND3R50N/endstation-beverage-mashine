package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DrinkServiceTest {


    @Mock
    private final DrinkMapper drinkMapper = mock(DrinkMapper.class);

    @Mock
    private final DrinkData drinkDataMock = mock(DrinkData.class);

    @Mock
    private final DrinkEntity drinkDataEntityMock = mock(DrinkEntity.class);

    @Mock
    private final DrinkRepository drinkRepository = mock(DrinkRepository.class);

    DrinkService drinkService = new DrinkService(drinkMapper, drinkRepository);

    @Test
    void createDrink() {
        // when
        when(drinkMapper.map(drinkDataMock)).thenReturn(drinkDataEntityMock);
        when(drinkRepository.save(any())).thenReturn(drinkDataEntityMock);
        when(drinkDataEntityMock.getDrinkId()).thenReturn(12L);
        ResponseEntity<DrinkDataResponse> result = drinkService.createDrink(drinkDataMock);
        // verify
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(result.getBody()).getDrinkId(), 12L);

    }
}