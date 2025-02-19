package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        /*
        This test is purposefully left empty
        It serves as a smoke test to ensure that the Spring application context loads successfully.

        If the context fails to load, this test will fail, indicating an issue with the configuration.
         */
    }

    @Test
    void mainTest() {
        EshopApplication.main(new String[]{});
    }

}
