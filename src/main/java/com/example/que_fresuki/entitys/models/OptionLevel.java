package com.example.que_fresuki.entitys.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    private String name;

    @OneToMany(mappedBy = "optionLevel")
    private List<ProductOption> options;
}
