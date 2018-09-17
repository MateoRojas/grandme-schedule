package org.grandma.schedule.repository;

import org.grandma.schedule.vo.PersonMessage;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public class CalendarRepository {

    public Collection<PersonMessage> findByDate(LocalDate date) {
        return null;
    }
}
