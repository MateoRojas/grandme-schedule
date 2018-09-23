package org.grandma.schedule.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class Cellphone {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCellphone")
    private Integer id;

    // Attributes

    private Integer idPerson;

    private String number;

    private Boolean whatsapp;

    private Boolean state;

    // Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson", insertable = false, updatable = false, referencedColumnName = "idPerson")
    private Person person;
}
