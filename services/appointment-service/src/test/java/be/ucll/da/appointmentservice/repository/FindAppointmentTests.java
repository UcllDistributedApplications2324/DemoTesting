package be.ucll.da.appointmentservice.repository;

import be.ucll.da.appointmentservice.domain.appointment.Appointment;
import be.ucll.da.appointmentservice.domain.appointment.AppointmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FindAppointmentTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void getAppointmentsOnDayOnlyReturnsAcceptedAppointments() {
        var appointment1 = new Appointment(1, LocalDate.now(), "");
        appointment1.accept();

        var appointment2 = new Appointment(1, LocalDate.now(), "");

        entityManager.persist(appointment1);
        entityManager.persist(appointment2);

        var appointments = appointmentRepository.getAppointmentsOnDay(LocalDate.now());
        Assertions.assertThat(appointments.size()).isEqualTo(1);
    }
}
