package br.com.sw2you.realmeet.core;

import br.com.sw2you.realmeet.Application;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Para o spring usar profile que esta em application-integration-test.yml:
 * - ActiveProfiles(profiles = "integration-test")
 * <p>
 * Como vamos usar o Spring full nos testes configuramos a porta randomica e a classe de entrada:
 * - SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
 */
@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public abstract class BaseIntegrationTest {
    @Autowired
    private Flyway flyway;

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setup() {
        setupFlyway();
        setupEach();
    }

    protected void setupEach() {}

//    protected void setLocalHostBasePath(ApiClient apiClient, String path) throws MalformedURLException {
//        apiClient.setBasePath(new URL("http", "localhost", serverPort, path).toString());
//    }

    private void setupFlyway() {
        flyway.clean();
        flyway.migrate();
    }
}
