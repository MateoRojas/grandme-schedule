package org.grandma.schedule.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mateo
 * @since 1.0.0
 */
@Data
@Entity
public class Category {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Integer id;

    // Attributes

    private String abbreviation;

    private String name;

    private String description;

    private Boolean state;
}
