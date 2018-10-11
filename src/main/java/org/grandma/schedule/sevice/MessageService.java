package org.grandma.schedule.sevice;

import org.grandma.schedule.consumer.SmsMessageConsumer;
import org.grandma.schedule.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class MessageService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private SmsMessageConsumer smsMessageConsumer;

    @Autowired
    private TemplateService templateService;

    public void sendDailyMessage(LocalDate date) {

        smsMessageConsumer.sendMessages(
            templateService.getMessages(
                calendarRepository.findPersonMessageByDate(
                    date
                )
            )
        );
    }
}
