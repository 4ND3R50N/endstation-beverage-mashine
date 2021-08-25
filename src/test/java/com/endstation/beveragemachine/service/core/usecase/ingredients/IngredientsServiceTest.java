package com.endstation.beveragemachine.service.core.usecase.ingredients;

import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientResponse;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsEntity;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IngredientsServiceTest {

    @Mock
    private final IngredientsRepository ingredientsRepository = mock(IngredientsRepository.class);

    private final IngredientsService cut = new IngredientsService(ingredientsRepository);

    @Test
    public void shouldCreateIngredient() {
        // give
        long ingredientId = 12L;
        String name = "test ingredient";
        IngredientData.LiquidTypeEnum alcohol = IngredientData.LiquidTypeEnum.ALCOHOL;
        IngredientData ingredientData = IngredientData.builder()
                .name(name)
                .liquidType(alcohol)
                .build();
        IngredientsEntity ingredientsEntity = IngredientsEntity.builder()
                .ingredientId(ingredientId)
                .build();

        // when
        when(ingredientsRepository.save(any(IngredientsEntity.class))).thenReturn(ingredientsEntity);
        ResponseEntity<IngredientResponse> result = cut.createIngredient(ingredientData);

        // assert
        assertEquals(Objects.requireNonNull(result.getBody()).getIngredientId(), ingredientId);
    }

}