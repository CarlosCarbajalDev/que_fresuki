package com.example.que_fresuki.services;

import java.util.List;

import com.example.que_fresuki.entitys.models.ProductOption;

import io.micrometer.common.lang.NonNull;

public interface ProductOptionService {
    //Genera los metodos crud
    List<ProductOption> getAllProductOption();
    ProductOption getProductOption(@NonNull Long id);
    ProductOption saveProductOption(ProductOption body);
    ProductOption updateProductOption(@NonNull Long id, @NonNull ProductOption body);
    void deleteProductOption(@NonNull Long id);
}
