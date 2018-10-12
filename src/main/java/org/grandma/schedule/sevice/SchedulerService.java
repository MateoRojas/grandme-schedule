package org.grandma.schedule.sevice;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class SchedulerService {

    @Autowired
    private DateService dateService;

    @Autowired
    private MessageService messageService;

    public void sendDailyWeekDayMessage() {
        log.info("Sending daily weekday message");
        this.messageService.sendDailyMessage(this.dateService.currentLocalDate());
    }
}
