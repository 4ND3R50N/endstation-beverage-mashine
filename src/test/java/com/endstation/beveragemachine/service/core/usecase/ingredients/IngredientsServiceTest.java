package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientData.LiquidTypeEnum;
import com.endstation.beveragemachine.service.model.IngredientResponse;
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

class IngredientsServiceTest {

    @Mock
    private final IngredientMapper ingredientMapper = mock(IngredientMapper.class);

    @Mock
    private final IngredientsRepository IngredientsRepository = mock(IngredientsRepository.class);

    @Mock
    private final IngredientData ingredientDataOneMock = mock(IngredientData.class);

    @Mock
    private final IngredientData ingredientDataTwoMock = mock(IngredientData.class);

    @Mock
    private final IngredientEntity ingredientEntityMock = mock(IngredientEntity.class);

    @Mock
    private final IngredientEntity ingredientEntityTwoMock = mock(IngredientEntity.class);

    private final IngredientsService cut = new IngredientsService(ingredientMapper, IngredientsRepository);

    @Test
    public void shouldCreateIngredient() {
        // give
        long ingredientId = 12L;
        String name = "test ingredient";
        LiquidTypeEnum alcohol = LiquidTypeEnum.ALCOHOL;
        IngredientData ingredientData = IngredientData.builder()
                .name(name)
                .liquidType(alcohol)
                .build();
        IngredientEntity ingredientEntity = IngredientEntity.builder()
                .ingredientId(ingredientId)
                .build();

        // when
        when(IngredientsRepository.existsByName(ingredientData.getName())).thenReturn(false);
        when(ingredientMapper.map(ingredientData)).thenReturn(ingredientEntity);
        when(IngredientsRepository.save(any(IngredientEntity.class))).thenReturn(ingredientEntity);
        ResponseEntity<IngredientResponse> result = cut.createIngredient(ingredientData);

        // assert
        assertEquals(Objects.requireNonNull(result.getBody()).getIngredientId(), ingredientId);
    }

    @Test
    public void shouldReturnConflictDueDuplicates() {
        // give
        String name = "test ingredient";
        LiquidTypeEnum alcohol = LiquidTypeEnum.ALCOHOL;
        IngredientData ingredientData = IngredientData.builder()
                .name(name)
                .liquidType(alcohol)
                .build();

        // when
        when(IngredientsRepository.existsByName(ingredientData.getName())).thenReturn(true);
        ResponseEntity<IngredientResponse> result = cut.createIngredient(ingredientData);

        // assert
        assertEquals(result.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void shouldReturnAllIngredients() {
        // given
        IngredientEntity ingredientOne = IngredientEntity.builder()
                .ingredientId(1L)
                .name("Orange juice")
                .liquidType(LiquidTypeEnum.SOFTDRINK)
                .build();

        IngredientEntity ingredientTwo = IngredientEntity.builder()
                .ingredientId(1L)
                .name("Vodka")
                .liquidType(LiquidTypeEnum.ALCOHOL)
                .build();

        // when
        when(IngredientsRepository.findAll()).thenReturn(List.of(ingredientOne, ingredientTwo));
        when(ingredientMapper.map(ingredientOne)).thenReturn(ingredientDataOneMock);
        when(ingredientMapper.map(ingredientTwo)).thenReturn(ingredientDataTwoMock);
        ResponseEntity<List<IngredientData>> result = cut.getIngredients();

        // assert
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).size()).isEqualTo(2);
        assertThat(result.getBody().get(0)).isInstanceOf(IngredientData.class);
        assertThat(result.getBody().get(1)).isInstanceOf(IngredientData.class);


    }

    @Test
    public void shouldGetOneIngredient() {
        // given
        Long ingredientId = 12L;

        // when
        when(IngredientsRepository.findById(ingredientId)).thenReturn(Optional.of(ingredientEntityMock));
        when(ingredientMapper.map(ingredientEntityMock)).thenReturn(ingredientDataOneMock);
        ResponseEntity<IngredientData> result = cut.getIngredient(ingredientId);

        // verify
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertThat(result.getBody()).isInstanceOf(IngredientData.class);

    }

    @Test
    public void shouldReturnNotFoundIfNoIngredientByGet() {
        // given
        Long ingredientId = 12L;

        // when
        when(IngredientsRepository.findById(ingredientId)).thenReturn(Optional.empty());
        ResponseEntity<IngredientData> result = cut.getIngredient(ingredientId);

        // verify
        verify(ingredientMapper, times(0)).map((IngredientEntity) any());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void shouldUpdateOneIngredient() {
        // given
        Long ingredientId = 12L;

        // when
        when(IngredientsRepository.findById(ingredientId)).thenReturn(Optional.of(ingredientEntityMock));
        when(ingredientMapper.map(ingredientId, ingredientDataOneMock)).thenReturn(ingredientEntityTwoMock);

        ResponseEntity<Void> result = cut.updateIngredient(ingredientId, ingredientDataOneMock);

        // verify
        verify(IngredientsRepository, times(1)).save(ingredientEntityTwoMock);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNotFoundIfNoIngredientByUpdate() {
        // given
        Long ingredientId = 12L;

        // when
        when(IngredientsRepository.findById(ingredientId)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = cut.updateIngredient(ingredientId, ingredientDataOneMock);

        // verify
        verify(ingredientMapper, times(0)).map(any(), any());
        verify(IngredientsRepository, times(0)).save(any());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}