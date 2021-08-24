package com.endstation.beveragemaschine.service.dataprovider.db.ingredients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class IngredientsEntity {
    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer ingredientId;



}
