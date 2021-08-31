package com.endstation.beveragemachine.service.dataprovider.db.drinks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkIngredientConceptionRepository extends JpaRepository<DrinkIngredientConceptionEntity, Long> {
}
