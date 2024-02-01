package com.example.que_fresuki.services;

import com.example.que_fresuki.entitys.models.Product;
import io.micrometer.common.lang.NonNull;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Product getProduct(@NonNull Long id);

    Product saveProduct(Product body);

    Product updateProduct(@NonNull Long id, @NonNull Product body);

    void deleteProduct(@NonNull Long id);

}
