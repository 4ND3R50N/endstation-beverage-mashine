package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsEntity;
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
    private final IngredientsRepository ingredientsRepository = mock(IngredientsRepository.class);

    @Mock
    private final IngredientData ingredientDataOneMock = mock(IngredientData.class);

    @Mock
    private final IngredientData ingredientDataTwoMock = mock(IngredientData.class);

    @Mock
    private final IngredientsEntity ingredientsEntityMock = mock(IngredientsEntity.class);

    @Mock
    private final IngredientsEntity ingredientsEntityTwoMock = mock(IngredientsEntity.class);

    private final IngredientsService cut = new IngredientsService(ingredientMapper, ingredientsRepository);

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
        IngredientsEntity ingredientsEntity = IngredientsEntity.builder()
                .ingredientId(ingredientId)
                .build();

        // when
        when(ingredientsRepository.existsByName(ingredientData.getName())).thenReturn(false);
        when(ingredientMapper.map(ingredientData)).thenReturn(ingredientsEntity);
        when(ingredientsRepository.save(any(IngredientsEntity.class))).thenReturn(ingredientsEntity);
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
        when(ingredientsRepository.existsByName(ingredientData.getName())).thenReturn(true);
        ResponseEntity<IngredientResponse> result = cut.createIngredient(ingredientData);

        // assert
        assertEquals(result.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void shouldReturnAllIngredients() {
        // given
        IngredientsEntity ingredientOne = IngredientsEntity.builder()
                .ingredientId(1L)
                .name("Orange juice")
                .liquidType(LiquidTypeEnum.SOFTDRINK)
                .build();

        IngredientsEntity ingredientTwo = IngredientsEntity.builder()
                .ingredientId(1L)
                .name("Vodka")
                .liquidType(LiquidTypeEnum.ALCOHOL)
                .build();

        // when
        when(ingredientsRepository.findAll()).thenReturn(List.of(ingredientOne, ingredientTwo));
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
        when(ingredientsRepository.findById(ingredientId)).thenReturn(Optional.of(ingredientsEntityMock));
        when(ingredientMapper.map(ingredientsEntityMock)).thenReturn(ingredientDataOneMock);
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
        when(ingredientsRepository.findById(ingredientId)).thenReturn(Optional.empty());
        ResponseEntity<IngredientData> result = cut.getIngredient(ingredientId);

        // verify
        verify(ingredientMapper, times(0)).map((IngredientsEntity) any());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void shouldUpdateOneIngredient() {
        // given
        Long ingredientId = 12L;

        // when
        when(ingredientsRepository.findById(ingredientId)).thenReturn(Optional.of(ingredientsEntityMock));
        when(ingredientMapper.map(ingredientId, ingredientDataOneMock)).thenReturn(ingredientsEntityTwoMock);

        ResponseEntity<Void> result = cut.updateIngredient(ingredientId, ingredientDataOneMock);

        // verify
        verify(ingredientsRepository, times(1)).save(ingredientsEntityTwoMock);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void shouldReturnNotFoundIfNoIngredientByUpdate() {
        // given
        Long ingredientId = 12L;

        // when
        when(ingredientsRepository.findById(ingredientId)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = cut.updateIngredient(ingredientId, ingredientDataOneMock);

        // verify
        verify(ingredientMapper, times(0)).map(any(), any());
        verify(ingredientsRepository, times(0)).save(any());
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}