package org.grandma.schedule.sevice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerServiceTest {

    @Mock
    private DateService dateService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private SchedulerService schedulerService;

    @Test
    public void shouldCallMessageServiceWithCurrentDateWhenInvoked() {

        LocalDate date = LocalDate.now();

        when(dateService.currentLocalDate()).thenReturn(date);

        schedulerService.sendDailyWeekDayMessage();

        verify(messageService).sendDailyMessage(date);
    }
}