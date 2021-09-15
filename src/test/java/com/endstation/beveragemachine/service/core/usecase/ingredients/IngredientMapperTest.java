package com.endstation.beveragemachine.service.core.usecase.Ingredients;

import com.endstation.beveragemachine.service.dataprovider.db.Ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.model.IngredientData;
import com.endstation.beveragemachine.service.model.IngredientData.LiquidTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientMapperTest {

    public static final String NAME = "Orange juice";
    public static final LiquidTypeEnum LIQUID_TYPE = LiquidTypeEnum.SOFTDRINK;
    IngredientMapper cut = new IngredientMapper();

    @Test
    public void shouldMapDataToEntity() {
        //given
        IngredientData ingredientData = IngredientData.builder()
                .name(NAME)
                .liquidType(LIQUID_TYPE)
                .build();


        // when
        IngredientEntity result = cut.map(ingredientData);

        // assert
        assertNull(result.getIngredientId());
        assertEquals(result.getName(), NAME);
        assertEquals(result.getLiquidType(), LIQUID_TYPE);
    }

    @Test
    public void shouldMapDataWithIdToEntity() {
        //given
        Long ingredientId = 12L;

        IngredientData ingredientData = IngredientData.builder()
                .name(NAME)
                .liquidType(LIQUID_TYPE)
                .build();


        // when
        IngredientEntity result = cut.map(ingredientId, ingredientData);

        // assert
        assertEquals(result.getIngredientId(), ingredientId);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getLiquidType(), LIQUID_TYPE);
    }

    @Test
    public void shouldMapEntityToData() {
        //given
        IngredientEntity ingredientEntity = IngredientEntity.builder()
                .ingredientId(12L)
                .name(NAME)
                .liquidType(LIQUID_TYPE)
                .build();


        // when
        IngredientData result = cut.map(ingredientEntity);

        // assert
        assertEquals(result.getName(), NAME);
        assertEquals(result.getLiquidType(), LIQUID_TYPE);
    }

}