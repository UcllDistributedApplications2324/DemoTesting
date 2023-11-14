package be.ucll.da.doctorservice.contract;

import be.ucll.da.doctorservice.DoctorServiceApplication;
import be.ucll.da.doctorservice.api.DoctorApiController;
import be.ucll.da.doctorservice.domain.Doctor;
import be.ucll.da.doctorservice.domain.DoctorService;
import be.ucll.da.doctorservice.web.DoctorController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Optional;

// @SpringBootTest(classes = DoctorServiceApplication.class)
public class BaseTestClass {

    @BeforeEach
    public void setup() {
        DoctorService doctorService = Mockito.mock(DoctorService.class);

        RestAssuredMockMvc.standaloneSetup(new DoctorApiController(new DoctorController(doctorService)));

        Mockito.when(doctorService.getDoctor(122))
                .thenReturn(Optional.of(new Doctor(122, "fieldOfExpertise", "firstName", "lastName", 1,  "address")));
    }
}
