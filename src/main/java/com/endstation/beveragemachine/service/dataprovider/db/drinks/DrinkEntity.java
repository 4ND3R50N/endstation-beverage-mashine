package com.endstation.beveragemachine.service.dataprovider.db.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.Audible;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drinks")
public class DrinkEntity extends Audible<String> {
    @Id
    @Column(name = "drink_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long drinkId;

    @Column(name = "visitor_id")
    private String visitorId;

    @OneToMany(
            mappedBy = "drink",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DrinkIngredientConceptionEntity> ingredientConceptions;

    @Column(unique = true)
    private String name;

    @Column(name = "is_basic_drink")
    private boolean isBasicDrink;
}
