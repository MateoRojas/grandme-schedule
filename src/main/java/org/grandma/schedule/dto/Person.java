package org.grandma.schedule.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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

    // Relations

    @OneToMany(mappedBy = "person", fetch =  FetchType.LAZY)
    private Collection<Cellphone> cellphones;
}
