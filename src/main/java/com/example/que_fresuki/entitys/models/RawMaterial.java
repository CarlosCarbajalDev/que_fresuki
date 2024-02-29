package com.example.que_fresuki.entitys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameRawMaterial;
    private Double totalQuantity;

    /*@OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.ALL)
    private List<ProductRawMaterial> productRawMaterials;*/
}
