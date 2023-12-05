package com.example.que_fresuki.repositorys;

import com.example.que_fresuki.entitys.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    Optional<RawMaterial> findByNameRawMaterial(String nameRawMaterial);
}
