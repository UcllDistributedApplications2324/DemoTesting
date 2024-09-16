package be.ucll.da.appointmentservice.unit;

import be.ucll.da.appointmentservice.client.doctor.api.model.DoctorsOnPayrollEvent;
import be.ucll.da.appointmentservice.domain.appointment.Appointment;
import be.ucll.da.appointmentservice.domain.appointment.AppointmentRepository;
import be.ucll.da.appointmentservice.domain.appointment.AppointmentRequestSaga;
import be.ucll.da.appointmentservice.adapters.messaging.RabbitMqMessageSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AppointmentRequestSagaTests {

    @Test
    public void whenReceivingEventWithoutDoctorsAnEmailIsSent() {
        var eventSender = Mockito.mock(RabbitMqMessageSender.class);
        var appointmentRepository = Mockito.mock(AppointmentRepository.class);
        var saga = new AppointmentRequestSaga(eventSender, appointmentRepository);

        DoctorsOnPayrollEvent event = new DoctorsOnPayrollEvent();
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(new Appointment(1, LocalDate.now(), "")));

        saga.executeSaga(1, new DoctorsOnPayrollEvent());
        verify(eventSender, times(1)).sendEmail(Mockito.any(), Mockito.any());
        verify(eventSender, times(0)).sendBookRoomCommand(Mockito.any(), Mockito.any());
    }
}
