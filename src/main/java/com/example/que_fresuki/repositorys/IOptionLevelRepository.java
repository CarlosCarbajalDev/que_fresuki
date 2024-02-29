package com.example.que_fresuki.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.que_fresuki.entitys.models.OptionLevel;

public interface IOptionLevelRepository extends JpaRepository<OptionLevel, Long> {
    Optional<OptionLevel> findByNameIgnoreCase(String name);
}
