package org.grandma.schedule.maker;

import com.github.javafaker.Faker;
import org.grandma.schedule.dto.Cellphone;

import static org.grandma.schedule.maker.JavaFaker.FAKER;

public class CellphoneMaker {

    private Integer id = FAKER.number().randomDigit();

    private Integer idPerson = FAKER.number().randomDigit();

    private String number = "0" + FAKER.numerify("#########");

    private Boolean whatsapp = FAKER.bool().bool();

    private Boolean state = FAKER.bool().bool();

    public CellphoneMaker withId(Integer id) {
        this.id = id;
        return this;
    }

    public CellphoneMaker withIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
        return this;
    }

    public CellphoneMaker withNumber(String number) {
        this.number = number;
        return this;
    }

    public CellphoneMaker withWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
        return this;
    }

    public CellphoneMaker withState(Boolean state) {
        this.state = state;
        return this;
    }

    public Cellphone build() {
        return new Cellphone(id, idPerson, number, whatsapp, state);
    }
}
