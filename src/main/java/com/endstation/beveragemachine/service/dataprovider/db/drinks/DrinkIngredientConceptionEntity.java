package com.endstation.beveragemachine.service.dataprovider.db.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.Audible;
import com.endstation.beveragemachine.service.dataprovider.db.Ingredients.IngredientEntity;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private DrinkEntity drink;

    @ManyToOne
    private IngredientEntity ingredient;

}
