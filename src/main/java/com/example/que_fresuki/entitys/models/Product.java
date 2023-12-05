package com.example.que_fresuki.entitys.models;

import com.example.que_fresuki.utils.enums.Tamanio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String size;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
}
