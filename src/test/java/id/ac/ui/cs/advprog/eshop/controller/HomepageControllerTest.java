package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HomepageControllerTest {

    @Test
    void testHomePage() {
        // Arrange
        HomepageController homepageController = new HomepageController();
        Model model = mock(Model.class);

        // Act
        String viewName = homepageController.homePage(model);

        // Assert
        assertEquals("Homepage", viewName);
    }

    @Test
    void testHomePageRequest() throws Exception {
        // Arrange: Setup MockMvc untuk simulasi HTTP request
        HomepageController homepageController = new HomepageController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homepageController).build();

        // Act & Assert
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Pastikan response 200 OK
                .andExpect(view().name("Homepage")); // Pastikan view yang dikembalikan benar
    }
}