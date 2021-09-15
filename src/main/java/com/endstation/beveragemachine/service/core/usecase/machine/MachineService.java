package com.endstation.beveragemachine.service.core.usecase.machine;

import com.endstation.beveragemachine.service.dataprovider.db.machine.BottleSlotsRepository;
import com.endstation.beveragemachine.service.model.BottleSlots;
import com.endstation.beveragemachine.service.model.MachineIngredientsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final BottleSlotsRepository bottleSlotsRepository;

    public ResponseEntity<List<BottleSlots>> getSlots() {
        return new ResponseEntity<>(bottleSlotsRepository.findAll().stream()
                .map(bottleSlotEntity -> BottleSlots.builder()
                        .slotId(bottleSlotEntity.getSlotId())
                        .ingredientId(bottleSlotEntity.getIngredient().getIngredientId())
                        .build())
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity<List<BottleSlots>> setSlots(MachineIngredientsRequest machineIngredientsRequest) {
        return null;
    }
}
