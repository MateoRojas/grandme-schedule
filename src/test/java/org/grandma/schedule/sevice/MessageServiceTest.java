package org.grandma.schedule.sevice;

import org.grandma.schedule.consumer.SmsMessageConsumer;
import org.grandma.schedule.repository.CalendarRepository;
import org.grandma.schedule.vo.CellphoneMessage;
import org.grandma.schedule.vo.PersonMessage;
import org.grandma.schedule.vo.SmsMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private TemplateService templateService;

    @Mock
    private SmsMessageConsumer smsMessageConsumer;

    @InjectMocks
    private MessageService messageService;

    private Collection<PersonMessage> peopleMessages;

    private Collection<SmsMessage> smsMessages;

    private LocalDate date;

    private String firstNumber;

    private String secondNumber;

    private String thirdNumber;

    private String mateo;

    private String milton;

    private String message;

    @Before
    public void setup() {

        this.date = LocalDate.now();

        this.firstNumber = "0999445550";

        this.secondNumber = "0988817485";

        this.thirdNumber = "0945738457";

        this.mateo = "Mateo";

        this.milton = "Milton";

        this.message = "test message";

        this.smsMessages = Arrays.asList(firstMessage(), secondMessage(), thirdMessage());

        this.peopleMessages = Arrays.asList(personMessageOne(), personMessageSecond());

        when(calendarRepository.fetchPeopleToSendMessageByDate(any(LocalDate.class)))
            .thenReturn(peopleMessages);

        when(templateService.getMessages(anyCollection()))
            .thenReturn(smsMessages);
    }

    @Test
    public void shouldCallFetchPeopleToSendMessageByDateWhenSendDailyMessageInvoked() {

        messageService.sendDailyMessage(date);

        verify(calendarRepository).fetchPeopleToSendMessageByDate(date);
    }

    @Test
    public void shouldCallParseSmsTextMessageWhenSendDailyMessageInvoked() {

        messageService.sendDailyMessage(date);

        verify(templateService).getMessages(peopleMessages);
    }

    @Test
    public void shouldCallSendMessagesWhenSendDailyMessageInvoked() {

        messageService.sendDailyMessage(date);

        verify(smsMessageConsumer).sendMessages(smsMessages);
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