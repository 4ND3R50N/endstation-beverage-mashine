package com.endstation.beveragemachine.service.core.usecase.machine;

import com.endstation.beveragemachine.service.dataprovider.db.ingredients.IngredientEntity;
import com.endstation.beveragemachine.service.dataprovider.db.machine.BottleSlotEntity;
import com.endstation.beveragemachine.service.dataprovider.db.machine.BottleSlotsRepository;
import com.endstation.beveragemachine.service.model.BottleSlots;
import com.endstation.beveragemachine.service.model.MachineIngredientsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final BottleSlotsRepository bottleSlotsRepository;
    private final EntityManager entityManager;

    public ResponseEntity<List<BottleSlots>> getSlots() {
        return new ResponseEntity<>(collectSlots(),
                HttpStatus.OK);
    }

    public ResponseEntity<List<BottleSlots>> setSlots(MachineIngredientsRequest machineIngredientsRequest) {
        // todo: add check for slot id (only 1 - 6 is allowed)
        bottleSlotsRepository.save(BottleSlotEntity.builder()
                .slotId(machineIngredientsRequest.getSlotId().longValue())
                .ingredient(entityManager.find(IngredientEntity.class, machineIngredientsRequest.getIngredientId().longValue()))
                .build());
        List<BottleSlots> slots = collectSlots();
        // todo: sync current slots to the beverage machine
        return new ResponseEntity<>(slots, HttpStatus.CREATED);
    }

    private List<BottleSlots> collectSlots() {
        return bottleSlotsRepository.findAll().stream()
                .map(bottleSlotEntity -> BottleSlots.builder()
                        .slotId(bottleSlotEntity.getSlotId())
                        .ingredientId(bottleSlotEntity.getIngredient().getIngredientId())
                        .build())
                .collect(Collectors.toList());
    }
}
