package be.ucll.da.apigateway.contract;

import be.ucll.da.apigateway.client.doctor.ApiClient;
import be.ucll.da.apigateway.client.doctor.api.DoctorApi;
import be.ucll.da.apigateway.client.doctor.model.ApiDoctor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        ids = {"be.ucll.da:doctor-service:0.0.1-SNAPSHOT:stubs:6565"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class DoctorServiceIntegrationTests {

    @Test
    void get_doctor() {
        ApiClient apiClient = new ApiClient(new RestTemplate());
        apiClient.setBasePath("http://localhost:6565");

        DoctorApi api = new DoctorApi(apiClient);
        ApiDoctor doctor = api.getDoctorById(122);

        Assertions.assertThat(doctor.getId()).isEqualTo(122);
        Assertions.assertThat(doctor.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(doctor.getLastName()).isEqualTo("lastName");
        Assertions.assertThat(doctor.getAge()).isEqualTo(1);
        Assertions.assertThat(doctor.getAddress()).isEqualTo("address");
    }
}
