package org.grandma.schedule.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonMessageTest {

    private PersonMessage personMessage;

    private String firstNumber;

    private String secondNumber;

    @Before
    public void setup() {

        this.firstNumber = "0988817485";

        this.secondNumber = "0999445550";

        this.personMessage = getPersonMessage();
    }

    private PersonMessage getPersonMessage() {

        return new PersonMessage()
            .setFirstName("Milton")
            .setCellphones(Arrays.asList(
                new CellphoneMessage()
                        .setNumber(firstNumber)
                        .setWhatsapp(Boolean.FALSE),

                new CellphoneMessage()
                        .setNumber(secondNumber)
                        .setWhatsapp(Boolean.TRUE)
            ));
    }

    @Test
    public void shouldReturnCellphoneNumbersAsStringWhenCellphoneInvoked() {

        Collection<String> numbers = personMessage.cellphoneNumbers();

        assertThat(numbers)
            .contains(firstNumber, secondNumber);
    }
}