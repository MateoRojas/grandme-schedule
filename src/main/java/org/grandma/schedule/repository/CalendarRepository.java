package org.grandma.schedule.repository;

import org.grandma.schedule.model.Calendar;
import org.grandma.schedule.vo.PersonMessage;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

    Collection<PersonMessage> findByDate(LocalDate date);
}
