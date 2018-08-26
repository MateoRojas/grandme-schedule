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
import java.time.LocalDate;

/**
 * @author mateo
 * @since 1.0.0
 */
@Data
@Entity
public class Calendar {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCalendar")
    private Integer id;

    // Attributes

    private Integer idPerson;

    private Integer idMode;

    private LocalDate date;

    private Boolean state;

    // Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMode")
    private Catalog mode;
}
