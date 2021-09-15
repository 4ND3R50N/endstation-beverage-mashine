package com.endstation.beveragemachine.service.entrypoints.api.machine;

import com.endstation.beveragemachine.service.api.BeverageMachineApi;
import com.endstation.beveragemachine.service.api.DrinksApiDelegate;
import com.endstation.beveragemachine.service.core.usecase.drinks.DrinkService;
import com.endstation.beveragemachine.service.core.usecase.Ingredients.IngredientsService;
import com.endstation.beveragemachine.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MachineControllerImpl implements BeverageMachineApi {



    public ResponseEntity<List<BottleSlots>> getSlots() {
        return getDelegate().getSlots();
    }
}
