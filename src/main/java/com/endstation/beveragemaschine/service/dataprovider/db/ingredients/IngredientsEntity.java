package com.endstation.beveragemaschine.service.dataprovider.db.ingredients;

import com.endstation.beveragemaschine.service.model.IngredientData.LiquidTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class IngredientsEntity {
    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long ingredientId;

    @Column(unique = true)
    private String name;

    @Column(name = "liquid_type")
    @Enumerated(EnumType.STRING)
    private LiquidTypeEnum liquidType;


}
