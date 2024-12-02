package be.ucll.da.doctorservice.contract;

import be.ucll.da.doctorservice.DoctorServiceApplication;
import be.ucll.da.doctorservice.api.DoctorApiController;
import be.ucll.da.doctorservice.domain.Doctor;
import be.ucll.da.doctorservice.domain.DoctorService;
import be.ucll.da.doctorservice.adapters.web.incoming.DoctorController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

// This annotation is not necessary since we are only testing the integration layer
// We don't need to start up the entire class

// @SpringBootTest(classes = DoctorServiceApplication.class)
public class DoctorBase {

    @BeforeEach
    public void setup() {
        DoctorService doctorService = Mockito.mock(DoctorService.class);

        RestAssuredMockMvc.standaloneSetup(new DoctorApiController(new DoctorController(doctorService)));

        Mockito.when(doctorService.getDoctor(122))
                .thenReturn(Optional.of(new Doctor(122, "fieldOfExpertise", "firstName", "lastName", 1,  "address")));
    }
}
