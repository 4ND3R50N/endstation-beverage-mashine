package com.endstation.beveragemachine.service.core.usecase.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkEntity;
import com.endstation.beveragemachine.service.dataprovider.db.drinks.DrinkIngredientConceptionEntity;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.model.DrinkData;
import com.endstation.beveragemachine.service.model.DrinkIngredient;
import com.endstation.beveragemachine.service.model.DrinkIngredient.QuantityTypeEnum;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DrinkMapperTest {

    @Mock
    private final EntityManager entityManager = mock(EntityManager.class);

    DrinkMapper drinkMapper = new DrinkMapper(entityManager);

    @Test
    void mapDrinkDataToEntity() {
        // given
        DrinkData drinkData = DrinkData.builder()
                .name("Orange juice")
                .isBasicDrink(true)
                .visitorId("Lars P.")
                .ingredients(List.of(DrinkIngredient.builder()
                        .ingredientId(1L)
                        .amount(BigDecimal.valueOf(1))
                        .quantityType(QuantityTypeEnum.CL)
                        .build()))
                .build();
        IngredientEntity ingredientEntity = IngredientEntity.builder()
                .ingredientId(1L)
                .build();
        // when
        when(entityManager.find(IngredientEntity.class, 1L)).thenReturn(ingredientEntity);
        DrinkEntity result = drinkMapper.map(drinkData);

        // assert
        assertEquals(result.getName(), drinkData.getName());
        assertEquals(result.isBasicDrink(), drinkData.getIsBasicDrink());
        assertEquals(result.getVisitorId(), drinkData.getVisitorId());

        DrinkIngredientConceptionEntity drinkIngredientConceptionEntity = result.getIngredientConceptions().get(0);
        DrinkIngredient drinkIngredient = drinkData.getIngredients().get(0);
        assertEquals(drinkIngredientConceptionEntity.getAmount(),
                drinkIngredient.getAmount().intValue());
        assertEquals(drinkIngredientConceptionEntity.getQuantityType(),
                drinkIngredient.getQuantityType());
        assertEquals(drinkIngredientConceptionEntity.getIngredient().getIngredientId(),
                drinkIngredient.getIngredientId());
    }
}