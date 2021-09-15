package com.endstation.beveragemachine.service.entrypoints.api.machine;

import com.endstation.beveragemachine.service.api.BeverageMachineApiDelegate;
import com.endstation.beveragemachine.service.model.BottleSlots;
import com.endstation.beveragemachine.service.model.MachineIngredientsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MachineControllerImpl implements BeverageMachineApiDelegate {

    public ResponseEntity<List<BottleSlots>> getSlots() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<BottleSlots>> setSlots(MachineIngredientsRequest machineIngredientsRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
