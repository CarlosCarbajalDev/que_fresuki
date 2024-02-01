package com.example.que_fresuki.entitys.models;

import com.example.que_fresuki.utils.enums.Tamanio;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String size;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_raw_material",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "raw_material_id")
    )
    //@JsonManagedReference
    private List<RawMaterial> rawMaterials;
}
