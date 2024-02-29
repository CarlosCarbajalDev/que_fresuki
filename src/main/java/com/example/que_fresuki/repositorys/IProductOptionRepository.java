package com.example.que_fresuki.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.que_fresuki.entitys.models.ProductOption;

public interface IProductOptionRepository extends JpaRepository<ProductOption, Long> {
    Optional<ProductOption> findByNameIgnoreCase(String name);
}
