package org.grandma.schedule.consumer;


import org.grandma.schedule.vo.NexmoMessage;
import org.grandma.schedule.vo.SmsMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SmsMessageConsumerTest {

    @Mock
    private CommonRestConsumer commonRestConsumer;

    @InjectMocks
    private SmsMessageConsumer messageConsumer;

    private SmsMessage firstMessage;

    private SmsMessage secondMessage;

    @Before
    public void messageSetup() {

        this.firstMessage = firstMessage();
        this.secondMessage = secondMessage();
    }

    @Test
    public void shouldCallRestMessageServiceTwiceWhenTwoMessagesPassed() {

        NexmoMessage firstNexmoMessage = firstNexmoMessage();
        NexmoMessage secondNexmoMessage = secondNexmoMessage();
        Collection<SmsMessage> messages = Arrays.asList(firstMessage, secondMessage);

        messageConsumer.sendMessages(messages);

        verify(commonRestConsumer).postFormUrlEncoded(any(String.class), eq(firstNexmoMessage.valueMap()), eq(String.class));
        verify(commonRestConsumer).postFormUrlEncoded(any(String.class), eq(secondNexmoMessage.valueMap()), eq(String.class));
    }

    private NexmoMessage secondNexmoMessage() {

        return new NexmoMessage(
                SmsMessageConsumer.NEXMO_API_KEY,
                SmsMessageConsumer.NEXMO_API_SECRET,
                secondMessage
        );
    }

    private NexmoMessage firstNexmoMessage() {

        return new NexmoMessage(
                SmsMessageConsumer.NEXMO_API_KEY,
                SmsMessageConsumer.NEXMO_API_SECRET,
                firstMessage
        );
    }

    private SmsMessage firstMessage() {

        return new SmsMessage()
                .setText("Hello World!")
                .setTo("593988817485");
    }

    private SmsMessage secondMessage() {

        return new SmsMessage()
                .setText("This is a test!")
                .setTo("593999445550");
    }
}