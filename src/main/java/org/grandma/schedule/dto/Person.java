package org.grandma.schedule.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    private Integer idGender;

    private String firstName;

    private String secondName;

    private String fatherLastName;

    private String motherLastName;

    private String identification;

    private Boolean state;

    // Relations

    @OneToMany(mappedBy = "person", fetch =  FetchType.LAZY)
    private Collection<Cellphone> cellphones;
}
