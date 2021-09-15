package com.endstation.beveragemachine.service.dataprovider.db.Ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<IngredientEntity, Long> {
    boolean existsByName(String name);
}
