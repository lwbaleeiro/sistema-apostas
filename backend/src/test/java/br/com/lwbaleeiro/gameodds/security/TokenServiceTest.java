package br.com.lwbaleeiro.gameodds.security;

import br.com.lwbaleeiro.gameodds.modules.security.TokenService;
import br.com.lwbaleeiro.gameodds.modules.users.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Should generate a valid token")
    void generateToken_ValidUser_ReturnsToken() {

        var user = new UserEntity("Test User", "teste@example.com", "123456");

        String token = tokenService.generateToken(user);

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
        System.out.println("Generated token: " + token);
    }

    @Test
    @DisplayName("Should validate token and return subject (email)")
    void validateToken_ValidToken_ReturnsSubject() {

        var user = new UserEntity("Test User", "teste@example.com", "123456");
        String token = tokenService.generateToken(user);

        String subject = tokenService.validateToken(token);

        assertEquals("teste@example.com", subject);
    }

    @Test
    @DisplayName("Should return empty string for invalid token")
    void validateToken_InvalidToken_ReturnsEmpty() {

        String subject = tokenService.validateToken("invalid-string-token");
        assertEquals("", subject);
    }
}
