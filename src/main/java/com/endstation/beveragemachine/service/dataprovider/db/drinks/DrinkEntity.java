package com.endstation.beveragemachine.service.dataprovider.db.drinks;

import com.endstation.beveragemachine.service.dataprovider.db.Audible;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<DrinkIngredientConceptionEntity> ingredientConceptions = new ArrayList<>();

    @Column(unique = true)
    private String name;

    @Column(name = "is_basic_drink")
    private boolean isBasicDrink;

    public DrinkEntity(Long drinkId, String visitorId, List<DrinkIngredientConceptionEntity> conceptions, String name, boolean isBasicDrink) {
        this.drinkId = drinkId;
        this.visitorId = visitorId;
        this.name = name;
        this.isBasicDrink = isBasicDrink;
        this.ingredientConceptions = new ArrayList<>();

        for (DrinkIngredientConceptionEntity conception : conceptions) {
            addConception(conception);
        }
    }

    public DrinkEntity(String visitorId, String name, boolean isBasicDrink) {
        this.visitorId = visitorId;
        this.name = name;
        this.isBasicDrink = isBasicDrink;
    }


    public DrinkEntity(Long drinkId, String visitorId, String name, boolean isBasicDrink) {
        this.drinkId = drinkId;
        this.visitorId = visitorId;
        this.name = name;
        this.isBasicDrink = isBasicDrink;

    }


    public void addConception(DrinkIngredientConceptionEntity conception) {
        conception.setDrink(this);
        ingredientConceptions.add(conception);
    }

    public void removeConception(DrinkIngredientConceptionEntity conception) {
        ingredientConceptions.remove(conception);
        conception.setDrink(null);
    }
}
