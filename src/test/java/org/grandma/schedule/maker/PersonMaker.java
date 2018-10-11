package org.grandma.schedule.maker;

import org.grandma.schedule.dto.Person;

import static org.grandma.schedule.maker.JavaFaker.FAKER;

public class PersonMaker {

    private Integer id = FAKER.number().randomDigit();

    private Integer idGender = FAKER.number().randomDigit();

    private String firstName = FAKER.name().firstName();

    private String secondName = FAKER.name().firstName();

    private String fatherLastName = FAKER.name().lastName();

    private String motherLastName = FAKER.name().lastName();

    private String identification = FAKER.numerify("##########");

    private Boolean state = FAKER.bool().bool();

    public PersonMaker withId(Integer id) {
        this.id = id;
        return this;
    }

    public PersonMaker withIdGender(Integer idGender) {
        this.idGender = idGender;
        return this;
    }

    public PersonMaker withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonMaker withState(Boolean state) {
        this.state = state;
        return this;
    }

    public Person build() {
        return new Person(id, idGender, firstName, secondName, fatherLastName, motherLastName, identification, state);
    }
}
