package org.grandma.schedule.sevice;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.grandma.schedule.dto.Calendar;
import org.grandma.schedule.dto.Cellphone;
import org.grandma.schedule.dto.Person;
import org.grandma.schedule.maker.CellphoneMaker;
import org.grandma.schedule.maker.PersonMaker;
import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SchedulerServiceInteg {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SchedulerService schedulerService;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void givenThereAreTwoPeopleAssignedTodayWhenSendDailyMessageThenTwoSmsSend() {

        LocalDate date = LocalDate.now();
        Person firstPerson = makerPerson();
        Person secondPerson = makerPerson();
        Cellphone firstPersonCellphoneOne = makeCellphone(1);
        Cellphone firstPersonCellphoneTwo = makeCellphone(1);
        Cellphone secondPersonCellphoneOne = makeCellphone(2);
        Calendar calendarFirstPerson = makeCalendar(date, 1);
        Calendar calendarSecondPerson = makeCalendar(date, 2);

        saveObjects(firstPerson, secondPerson, firstPersonCellphoneOne,
                firstPersonCellphoneTwo, secondPersonCellphoneOne, calendarFirstPerson, calendarSecondPerson);

        stubFor(post(urlEqualTo("/sms/json"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
            )
        );

        schedulerService.sendDailyWeekDayMessage();

        verify(postRequestedFor(urlEqualTo("/sms/json"))
                .withRequestBody(containing("to=593" + firstPersonCellphoneOne.getNumber().substring(1)))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8")));

        verify(postRequestedFor(urlEqualTo("/sms/json"))
                .withRequestBody(containing("to=593" + firstPersonCellphoneTwo.getNumber().substring(1)))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8")));

        verify(postRequestedFor(urlEqualTo("/sms/json"))
                .withRequestBody(containing("to=593" + secondPersonCellphoneOne.getNumber().substring(1)))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded;charset=UTF-8")));
    }

    private Calendar makeCalendar(LocalDate date, Integer personId) {
        return new Calendar().setDate(date).setIdPerson(personId).setState(true);
    }

    private Person makerPerson() {
        return new PersonMaker().withId(null).withIdGender(null).withState(true).build();
    }

    private Cellphone makeCellphone(Integer personId) {
        return new CellphoneMaker().withIdPerson(personId).withId(null).withState(true).build();
    }

    private void saveObjects(Object... objects) {
        Stream.of(objects)
            .forEachOrdered(object -> {
                sessionFactory.getCurrentSession().save(object);
            });
    }
}
