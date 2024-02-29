package com.example.que_fresuki.repositorys;

import com.example.que_fresuki.entitys.models.Product;
import com.example.que_fresuki.entitys.models.ProductRawMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, Long> {
    //Optional<Product> findByNameIgnoreCase(String name);
}
