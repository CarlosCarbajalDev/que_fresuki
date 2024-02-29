package com.example.que_fresuki.entitys.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * La clase Sale representa una venta en tu aplicación.
 * Cada venta puede tener varios SaleItems, que representan los productos individuales vendidos en la venta.
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comand {
    //El ID único para esta venta. Este campo se mapeará a una columna de ID en la tabla de la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Los artículos vendidos en esta venta.
     * @OneToMany: Esta anotación indica que hay una relación de uno a muchos entre Sale y SaleItem.
     * @mappedBy: Esta propiedad indica que la entidad SaleItem es la propietaria de la relación.
     * @cascade: Esta propiedad indica que las operaciones que se realicen en una venta se propagarán a sus SaleItems.
     */
    @OneToMany(mappedBy = "comand", cascade = CascadeType.ALL)
    private List<ComandItem> comandItems;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSelling;
}
