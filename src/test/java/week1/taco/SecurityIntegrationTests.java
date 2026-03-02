package week1.taco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void loginPageAccessible() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    void rootRedirectsToLoginWhenNotAuthenticated() throws Exception {
        // root has no mapping in this app; use a protected endpoint instead
        mockMvc.perform(get("/design"))
            .andExpect(status().is3xxRedirection());
    }

        @Test
        void authenticatedAccessAllowed() throws Exception {
        // instead of relying on @WithMockUser, explicitly add a user to the request
        mockMvc.perform(get("/design").with(
            org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("user").roles("USER")
        ))
            .andExpect(status().isOk());
        }
}
