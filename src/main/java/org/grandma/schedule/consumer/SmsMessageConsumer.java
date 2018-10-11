package org.grandma.schedule.consumer;

import org.grandma.schedule.model.NexmoMessage;
import org.grandma.schedule.model.SmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author mateo
 * @since 1.0.0
 */
@Component
public class SmsMessageConsumer {

    public static final String NEXMO_API_KEY = "c07add87";

    public static final String NEXMO_API_SECRET = "4FuqPoDpcU28gAgB";

    @Autowired
    private Environment environment;

    @Autowired
    private CommonRestConsumer commonRestConsumer;

    /**
     * Send the provided sms messages.
     *
     * @param messages the messages to be sent
     */
    public void sendMessages(Collection<SmsMessage> messages) {

        messages
            .stream()
            .map(message -> new NexmoMessage(NEXMO_API_KEY, NEXMO_API_SECRET, message).valueMap())
            .forEach(message ->
                this.commonRestConsumer.postFormUrlEncoded(
                        environment.getProperty("org.grandma.nexmo.api.url"), message, String.class)
            );
    }
}
