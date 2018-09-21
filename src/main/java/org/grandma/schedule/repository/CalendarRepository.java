package org.grandma.schedule.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.grandma.schedule.dto.Calendar;
import org.grandma.schedule.dto.QCalendar;
import org.grandma.schedule.dto.QCellphone;
import org.grandma.schedule.dto.QPerson;
import org.grandma.schedule.model.CellphoneMessage;
import org.grandma.schedule.model.PersonMessage;
import org.grandma.schedule.model.QCellphoneMessage;
import org.grandma.schedule.model.QPersonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public class CalendarRepository {

    @Autowired
    private HibernateQueryFactory query;

    public Collection<PersonMessage> findByDate(LocalDate date) {

        QCalendar qCalendar = QCalendar.calendar;
        QPerson qPerson = QPerson.person;
        QCellphone qCellphone = QCellphone.cellphone;

        QPersonMessage qPersonMessage = QPersonMessage.personMessage;
        QCellphoneMessage qCellphoneMessage = QCellphoneMessage.cellphoneMessage;

        return query.from(qCalendar)
            .innerJoin(qCalendar.person, qPerson)
            .innerJoin(qPerson.cellphones, qCellphone)
            .where(
                qCalendar.date.eq(date),
                qCalendar.state.eq(true),
                qPerson.state.eq(true),
                qCellphone.state.eq(true)
            ).transform(GroupBy.groupBy(qCalendar.id)
                .list(
                    Projections.fields(
                        PersonMessage.class,
                        qPerson.firstName.as(qPersonMessage.firstName),

                        GroupBy.list(
                            Projections.fields(
                                CellphoneMessage.class,
                                qCellphone.number.as(qCellphoneMessage.number),
                                qCellphone.whatsapp.as(qCellphoneMessage.whatsapp)
                            )
                        ).as(qPersonMessage.cellphones.getMetadata().getName())
                    )
                )
            );
    }
}
