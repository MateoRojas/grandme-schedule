package org.grandma.schedule.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Autowired
    private DateService dateService;

    @Autowired
    private MessageService messageService;

    public void sendDailyWeekDayMessage() {
        this.messageService.sendDailyMessage(this.dateService.currentLocalDate());
    }
}
