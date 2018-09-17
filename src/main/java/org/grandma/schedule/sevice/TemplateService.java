package org.grandma.schedule.sevice;

import org.grandma.schedule.model.PersonMessage;
import org.grandma.schedule.model.SmsMessage;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TemplateService {

    public String getMessage(PersonMessage personMessage) {
        return personMessage.getFirstName() +  ", mensaje de prueba";
    }

    public Collection<SmsMessage> getMessages(Collection<PersonMessage> peopleMessages) {

        return peopleMessages
            .stream()
            .flatMap(this::getMessagesFromPersonMessage)
            .collect(Collectors.toList());
    }

    private Stream<SmsMessage> getMessagesFromPersonMessage(PersonMessage personMessage) {

        String message = this.getMessage(personMessage);

        return personMessage
                .cellphoneNumbers()
                .stream()
                .map(number -> new SmsMessage().setTo(number).setText(message));
    }
}
