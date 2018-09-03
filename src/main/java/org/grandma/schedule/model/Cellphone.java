package org.grandma.schedule.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Cellphone {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCellphone")
    private Integer id;

    // Attributes

    private Integer idPerson;

    private Integer number;

    private Boolean whatsapp;

    private Boolean state;

    // Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person person;
}
