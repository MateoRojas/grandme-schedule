package org.grandma.schedule.model;

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
public class Person {

    // Id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private Integer id;

    // Attributes

    private String firstName;

    private String secondName;

    private String fatherLastName;

    private String motherLastName;

    private String identificaction;

    private Boolean state;
}
