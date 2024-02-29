package com.example.que_fresuki.entitys.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * La clase SaleItem representa un artículo individual en una venta.
 * Cada SaleItem está asociado con una venta y un producto.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComandItem {
    //El ID único para este SaleItem. Este campo se mapeará a una columna de ID en la tabla de la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * La venta a la que pertenece este SaleItem.
     * @ManyToOne: Esta anotación indica que hay una relación de muchos a uno entre SaleItem y Sale.
     * @JoinColumn: Esta anotación indica que este campo se mapeará a una columna de clave externa en la tabla de la base de datos.
     */
    @ManyToOne
    @JoinColumn(name = "comand_id")
    private Comand comand;

    /**
     * El producto que se está vendiendo en este SaleItem.
     * @ManyToOne: Esta anotación indica que hay una relación de muchos a uno entre SaleItem y Product.
     * @JoinColumn: Esta anotación indica que este campo se mapeará a una columna de clave externa en la tabla de la base de datos.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany
    @JoinTable(
        name = "comanditem_productoption", 
        joinColumns = @JoinColumn(name = "comanditem_id"), 
        inverseJoinColumns = @JoinColumn(name = "productoption_id"))
    private List<ProductOption> optionsSelected;

    /**
     * La cantidad de este producto que se está vendiendo en este SaleItem.
     */
    private int quantity;

}
