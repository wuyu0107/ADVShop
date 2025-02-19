package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    @Test
    void homeReturnsHomepage() {
        HomeController controller = new HomeController();
        String home = controller.home();
        assertEquals("Homepage", home);
    }
}
