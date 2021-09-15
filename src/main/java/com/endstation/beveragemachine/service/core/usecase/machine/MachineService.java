package com.endstation.beveragemachine.service.core.usecase.machine;

import com.endstation.beveragemachine.service.dataprovider.db.machine.BottleSlotsRepository;
import com.endstation.beveragemachine.service.model.BottleSlots;
import com.endstation.beveragemachine.service.model.MachineIngredientsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final BottleSlotsRepository bottleSlotsRepository;

    public ResponseEntity<List<BottleSlots>> getSlots() {
        return bottleSlotsRepository.findAll().stream().map(bottleSlotEntity -> {
            BottleSlots.builder()
                    .ingredientId(bottleSlotEntity.getIngredient().getIngredientId())
                    .build();

        });
    }

    public ResponseEntity<List<BottleSlots>> setSlots(MachineIngredientsRequest machineIngredientsRequest) {
        return null;
    }
}
