package com.example.que_fresuki.entitys.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    private String name;

    //0- postres
    //1- hamburguesas
    private int type;

    //Verificar si es necesario o no
    private double cost;

    @ManyToOne
    @JoinColumn(name = "option_level_id")
    @JsonBackReference
    private OptionLevel optionLevel;
}
