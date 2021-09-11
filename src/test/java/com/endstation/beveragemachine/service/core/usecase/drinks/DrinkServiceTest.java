package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkRepository;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkDataResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DrinkServiceTest {


    @Mock
    private final DrinkMapper drinkMapper = mock(DrinkMapper.class);

    @Mock
    private final DrinkData drinkDataMock = mock(DrinkData.class);

    @Mock
    private final DrinkEntity drinkDataEntityOneMock = mock(DrinkEntity.class);

    @Mock
    private final DrinkEntity drinkDataEntityTwoMock = mock(DrinkEntity.class);

    @Mock
    private final DrinkRepository drinkRepository = mock(DrinkRepository.class);

    DrinkService drinkService = new DrinkService(drinkMapper, drinkRepository);

    @Test
    void shouldCreateDrink() {
        // when
        when(drinkMapper.map(drinkDataMock)).thenReturn(drinkDataEntityOneMock);
        when(drinkRepository.save(any())).thenReturn(drinkDataEntityOneMock);
        when(drinkDataEntityOneMock.getDrinkId()).thenReturn(12L);
        ResponseEntity<DrinkDataResponse> result = drinkService.createDrink(drinkDataMock);
        // verify
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(result.getBody()).getDrinkId(), 12L);

    }

    @Test
    void shouldGetDrinks() {
        // when
        when(drinkRepository.findAll()).thenReturn(List.of(drinkDataEntityOneMock));
        when(drinkMapper.map(drinkDataEntityOneMock)).thenReturn(drinkDataMock);
        ResponseEntity<List<DrinkData>> result = drinkService.getDrinks();
        // verify
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(result.getBody()).size(), 1);

    }

    @Test
    public void shouldGetOneIngredient() {
        // given
        Long drinkId = 12L;

        // when
        when(drinkRepository.findById(drinkId)).thenReturn(Optional.of(drinkDataEntityOneMock));
        when(drinkMapper.map(drinkDataEntityOneMock)).thenReturn(drinkDataMock);
        ResponseEntity<DrinkData> result = drinkService.getDrinkById(drinkId);

        // verify
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertThat(result.getBody()).isInstanceOf(DrinkData.class);

    }

    @Test
    public void shouldUpdateOneDrink() {
        // given
        Long drinkId = 12L;

        // when
        when(drinkRepository.findById(drinkId)).thenReturn(Optional.of(drinkDataEntityOneMock));
        when(drinkMapper.map(drinkId, drinkDataMock)).thenReturn(drinkDataEntityTwoMock);

        ResponseEntity<Void> result = drinkService.updateDrink(drinkId, drinkDataMock);

        // verify
        verify(drinkRepository, times(1)).save(drinkDataEntityTwoMock);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldDeleteOneDrink() {
        // given
        Long drinkId = 12L;

        ResponseEntity<Void> result = drinkService.deleteDrinkBy(drinkId);

        // verify
        verify(drinkRepository, times(1)).deleteById(drinkId);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}