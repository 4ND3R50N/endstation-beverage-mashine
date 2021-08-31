package com.endstation.beveragemachine.service.dataprovider.db.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.Audible;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.endstation.beveragemachine.service.model.DrinkIngredient.QuantityTypeEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drink_ingredient_conception")
public class DrinkIngredientConceptionEntity extends Audible<String> {

    @Id
    @Column(name = "drink_ingredient_conception_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long drinkIngredientConceptionId;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "quantity_type")
    private QuantityTypeEnum quantityType;

    @ManyToOne(fetch = FetchType.LAZY)
    DrinkEntity drink;

    @ManyToOne(cascade = CascadeType.ALL)
    private IngredientEntity ingredient;

}
