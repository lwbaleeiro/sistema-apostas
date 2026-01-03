package br.com.lwbaleeiro.gameodds.users;

import br.com.lwbaleeiro.gameodds.modules.users.UserEntity;
import br.com.lwbaleeiro.gameodds.modules.users.UserRepository;
import br.com.lwbaleeiro.gameodds.modules.users.dto.AuthRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //Enable mockmvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {

        userRepository.deleteAll();
        var password = passwordEncoder.encode("123456");
        var user = new UserEntity("Test user", "test@example.com", password);
        userRepository.save(user);
    }

    @Test
    @DisplayName("Login with valid credentials should return 200 and token")
    void login_ValidCredentials_Returns200AndToken() throws Exception {

        var authRequest = new AuthRequest("test@example.com", "123456");

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    @DisplayName("Login with invalid credentials should return 401/403")
    void login_InvalidCredentials_ReturnError() throws Exception {

        var authRequest = new AuthRequest("teste@example.com", "wrong-password");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Access protected route without token sould return 403 Forbidden")
    void accessProtectedRoute_WithoutToken_Returns403() throws Exception {

        mockMvc.perform(get("/users")).andExpect(status().isForbidden());
    }
}
