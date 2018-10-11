package org.grandma.schedule.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
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

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Collection<Cellphone> cellphones;

    public Person() {
    }

    public Person(Integer id, Integer idGender, String firstName, String secondName, String fatherLastName, String motherLastName, String identification, Boolean state) {
        this.id = id;
        this.idGender = idGender;
        this.firstName = firstName;
        this.secondName = secondName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.identification = identification;
        this.state = state;
    }
}
