package com.endstation.beveragemaschine.service.dataprovider.db.ingredients;

import com.endstation.beveragemaschine.service.model.IngredientData.LiquidTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class IngredientsRepositoryTest {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @BeforeEach
    void setup() {
        ingredientsRepository.deleteAll();
    }

    @Test
    void injectedComponentsAreNotNull() {
        // given
        assertThat(ingredientsRepository).isNotNull();

        IngredientsEntity ingredientsEntity = IngredientsEntity.builder()
                .name("orange juice")
                .liquidType(LiquidTypeEnum.SOFTDRINK)
                .build();

        // when
        Long result = ingredientsRepository.save(ingredientsEntity).getIngredientId();

        // then
        assertThat(result).isNotNull();
        assertThat(ingredientsRepository.count()).isEqualTo(1);

    }

}