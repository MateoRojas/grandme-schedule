package org.grandma.schedule.repository;

import com.github.javafaker.Faker;
import org.grandma.schedule.config.PersistanceConfig;
import org.grandma.schedule.dto.Calendar;
import org.grandma.schedule.dto.Cellphone;
import org.grandma.schedule.dto.Person;
import org.grandma.schedule.model.CellphoneMessage;
import org.grandma.schedule.model.PersonMessage;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistanceConfig.class, CalendarRepository.class})
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CalendarRepositoryInteg {

    private Faker faker = new Faker();

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    public void givenThereAreTwoPeopleAssignedTodayWhenFindByDateThenCorrectPersonMessages() {

        LocalDate date = LocalDate.now();
        Boolean state = Boolean.TRUE;

        Boolean whatsappFirstNumberMateo = faker.bool().bool();
        Boolean whatsappSecondNumberMateo = faker.bool().bool();
        Boolean whatsappFirstNumberSilvia = faker.bool().bool();
        String firstNameMateo = faker.name().firstName();
        String firstNameSilvia = faker.name().firstName();
        String firstNumberMateo = "0" + faker.numerify("#########");
        String secondNumberMateo = "0" + faker.numerify("#########");
        String firstNumberSilvia = "0" + faker.numerify("#########");

        PersonMessage personMessageMateo = new PersonMessage()
            .setFirstName(firstNameMateo)
            .setCellphones(
                Arrays.asList(
                    new CellphoneMessage()
                        .setNumber(firstNumberMateo)
                        .setWhatsapp(whatsappFirstNumberMateo),
                    new CellphoneMessage()
                        .setNumber(secondNumberMateo)
                        .setWhatsapp(whatsappSecondNumberMateo)
                )
            );

        PersonMessage personMessageSilvia = new PersonMessage()
            .setFirstName(firstNameSilvia)
            .setCellphones(
                Arrays.asList(
                    new CellphoneMessage()
                        .setNumber(firstNumberSilvia)
                        .setWhatsapp(whatsappFirstNumberSilvia)
                )
            );

        Person mateo = new Person()
                .setFirstName(firstNameMateo)
                .setState(state);

        Person silvia = new Person()
                .setFirstName(firstNameSilvia)
                .setState(state);

        Cellphone firstCellphoneMateo = new Cellphone()
                .setIdPerson(1)
                .setNumber(firstNumberMateo)
                .setState(state)
                .setWhatsapp(whatsappFirstNumberMateo);

        Cellphone secondCellphoneMateo = new Cellphone()
                .setIdPerson(1)
                .setNumber(secondNumberMateo)
                .setState(state)
                .setWhatsapp(whatsappSecondNumberMateo);

        Cellphone firstCellphoneSilvia = new Cellphone()
                .setIdPerson(2)
                .setNumber(firstNumberSilvia)
                .setState(state)
                .setWhatsapp(whatsappFirstNumberSilvia);

        Calendar calendarMateo = new Calendar()
                .setDate(date)
                .setIdPerson(1)
                .setState(state);

        Calendar calendarSilvia = new Calendar()
                .setDate(date)
                .setIdPerson(2)
                .setState(state);

        sessionFactory.getCurrentSession().save(mateo);
        sessionFactory.getCurrentSession().save(silvia);
        sessionFactory.getCurrentSession().save(firstCellphoneMateo);
        sessionFactory.getCurrentSession().save(secondCellphoneMateo);
        sessionFactory.getCurrentSession().save(firstCellphoneSilvia);
        sessionFactory.getCurrentSession().save(calendarMateo);
        sessionFactory.getCurrentSession().save(calendarSilvia);

        Collection<PersonMessage> result = calendarRepository.findPersonMessageByDate(date);
        
        assertThat(result)
            .contains(personMessageMateo, personMessageSilvia);
    }
}