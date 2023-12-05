package com.example.que_fresuki.services;


import com.example.que_fresuki.entitys.models.Ingredient;
import lombok.NonNull;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getIngredients();

    Ingredient getIngredient(@NonNull Long id);

    Ingredient saveIngredient(Ingredient ingredient);

    Ingredient updateIngredient(@NonNull Long id, @NonNull Ingredient ingredient);
    void deleteIngredient(@NonNull Long id);


}
