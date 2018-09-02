package org.grandma.schedule.sevice;

import org.grandma.schedule.vo.CellphoneMessage;
import org.grandma.schedule.vo.PersonMessage;
import org.grandma.schedule.vo.SmsMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TemplateServiceTest {

    @Spy
    private TemplateService templateService;

    private String firstNumber;

    private String secondNumber;

    private String thirdNumber;

    private String mateo;

    private String milton;

    private String message;

    @Before
    public void setup() {

        this.firstNumber = "0999445550";

        this.secondNumber = "0988817485";

        this.thirdNumber = "0945738457";

        this.mateo = "Mateo";

        this.milton = "Milton";

        this.message = "test message";
    }

    @Test
    public void shouldReturnValidMessageWhenParseSmsTextMessageInvoked() {

        PersonMessage personMessage = personMessageSecond();
        String expected = "Milton, mensaje de prueba";

        String message = templateService.getMessage(personMessage);

        assertThat(message)
            .isEqualTo(expected);
    }

    @Test
    public void shouldReturnCollectionSmsMessagesWhenGetMessages() {

        Collection<PersonMessage> personMessages = Arrays.asList(personMessageOne(), personMessageSecond());

        doReturn(message)
            .when(templateService)
            .getMessage(any(PersonMessage.class));

        Collection<SmsMessage> smsMessages = templateService.getMessages(personMessages);

        assertThat(smsMessages)
            .contains(firstMessage(), secondMessage(), thirdMessage());
    }

    private SmsMessage firstMessage() {

        return new SmsMessage()
                .setText(message)
                .setTo(firstNumber);
    }

    private SmsMessage secondMessage() {

        return new SmsMessage()
                .setText(message)
                .setTo(secondNumber);
    }

    private SmsMessage thirdMessage() {

        return new SmsMessage()
                .setText(message)
                .setTo(thirdNumber);
    }

    private PersonMessage personMessageSecond() {

        return new PersonMessage()
            .setFirstName(milton)
            .setCellphones(
                Arrays.asList(
                    new CellphoneMessage()
                        .setNumber(firstNumber)
                        .setWhatsapp(Boolean.FALSE)
                )
            );
    }

    private PersonMessage personMessageOne() {

        return new PersonMessage()
            .setFirstName(mateo)
            .setCellphones(
                Arrays.asList(
                    new CellphoneMessage()
                        .setNumber(secondNumber)
                        .setWhatsapp(Boolean.FALSE),

                    new CellphoneMessage()
                        .setNumber(thirdNumber)
                        .setWhatsapp(Boolean.FALSE)
                )
            );
    }
}