package org.grandma.schedule.vo;

import org.grandma.schedule.consumer.SmsMessageConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class NexmoMessageTest {

    @Test
    public void shouldRetunValidMultiValueMapWhenValueMapInvoked() {

        NexmoMessage message = new NexmoMessage("123", "456", firstMessage());

        MultiValueMap<String, String> map = message.valueMap();

        assertThat(map)
            .isEqualTo(firstMessageMap());
    }

    private MultiValueMap firstMessageMap() {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("from", "Aplicacion Horarios");
        map.add("text", "Hello World!");
        map.add("to", "593988817485");
        map.add("api_key", "123");
        map.add("api_secret", "456");

        return map;
    }

    private SmsMessage firstMessage() {

        return new SmsMessage()
                .setText("Hello World!")
                .setTo("0988817485");
    }
}