package org.grandma.schedule.sevice;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {

    public LocalDate currentLocalDate() {
        return LocalDate.now();
    }
}
