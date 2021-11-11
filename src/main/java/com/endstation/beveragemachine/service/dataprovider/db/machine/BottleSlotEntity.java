package com.endstation.beveragemachine.service.dataprovider.db.machine;

import com.endstation.beveragemachine.service.dataprovider.db.Audible;
import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bottle_slots")
public class BottleSlotEntity extends Audible<String> {
    @Id
    @Column(name = "ingredient_id")
    private Long slotId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private IngredientEntity ingredient;
}

