package be.ucll.da.apigateway.web;

import be.ucll.da.apigateway.api.HospitalApiDelegate;
import be.ucll.da.apigateway.api.model.ApiAppointmentOverview;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class HospitalController implements HospitalApiDelegate {

    @Override
    public ResponseEntity<ApiAppointmentOverview> apiV1AppointmentDayGet(String dayString, Boolean useCqrs) {
        LocalDate day = LocalDate.parse(dayString, DateTimeFormatter.ISO_DATE);

        if (useCqrs) {
            return getUsingCqrs(day);
        } else {
            return getUsingApiComposition(day);
        }
    }

    // --- Code For Composition ---

    private ResponseEntity<ApiAppointmentOverview> getUsingApiComposition(LocalDate day) {
        throw new RuntimeException("Implement me!!");

        // 1. Call Appointment Service
        // 1.1 Get URI from discovery service
        // 1.2 Make Call

        // For every Appointment
            // 2. Call Patient Service with patient id
            // 2.1 Get URI from discovery service
            // 2.2 Make Call
            // 2.3 Fill in patient information

            // 3. Call Doctor Service with patient id
            // 3.1 Get URI from discovery service
            // 3.2 Make Call
            // 3.3 Fill in doctor information

        // Return overview
    }

    // --- Code For CQRS

    private ResponseEntity<ApiAppointmentOverview> getUsingCqrs(LocalDate day) {
        throw new RuntimeException("Implement me!!");
    }
}
