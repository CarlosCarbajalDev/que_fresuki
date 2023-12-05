package com.example.que_fresuki.services;


import com.example.que_fresuki.entitys.models.Ingredient;
import com.example.que_fresuki.entitys.models.RawMaterial;
import lombok.NonNull;

import java.util.List;

public interface RawMaterialService {
    List<RawMaterial> getRawMaterials();

    RawMaterial getRawMaterial(@NonNull Long id);

    RawMaterial saveRawMaterial(RawMaterial rawMaterial);

    RawMaterial updateRawMaterial(@NonNull Long id, @NonNull RawMaterial rawMaterial);
    void deleteRawMaterial(@NonNull Long id);


}
