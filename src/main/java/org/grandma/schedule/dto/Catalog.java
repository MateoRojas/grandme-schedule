package org.grandma.schedule.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author mateo
 * @since 1.0.0
 */
@Data
@Entity
public class Catalog {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCatalog")
    private Integer id;

    // Attributes

    private Integer idCategory;

    private String abbreviation;

    private String name;

    private String description;

    private Boolean state;

    // Relations

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
