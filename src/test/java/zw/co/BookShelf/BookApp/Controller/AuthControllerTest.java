package zw.co.BookShelf.BookApp.Controller;

import zw.co.BookShelf.BookApp.dto.UserDto.UserLoginDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserLoginResponseDto;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.Repository.UserRepository;
import zw.co.BookShelf.BookApp.security.JwtTokenUtil;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserRepository userRepository;

    @Test
    void login_successful_returnsTokenAndRole() throws Exception {
        UserLoginDto loginDto = new UserLoginDto();
        loginDto.setUsername("testuser");
        loginDto.setPassword("password");

        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "password");
        Mockito.when(authenticationManager.authenticate(any())).thenReturn(auth);
        Mockito.when(jwtTokenUtil.generateToken(eq("testuser"))).thenReturn("mocked-jwt-token");

        User user = User.builder()
                .userName("testuser")
                .role(User.Role.ADMIN)
                .build();
        Mockito.when(userRepository.findByUserName(eq("testuser"))).thenReturn(Optional.of(user));

        String json = """
                {
                  "username": "testuser",
                  "password": "password"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void login_userNotFound_returnsUnauthorized() throws Exception {
        Mockito.when(authenticationManager.authenticate(any())).thenReturn(
                new UsernamePasswordAuthenticationToken("nouser", "password")
        );
        Mockito.when(jwtTokenUtil.generateToken(eq("nouser"))).thenReturn("mocked-jwt-token");
        Mockito.when(userRepository.findByUserName(eq("nouser"))).thenReturn(Optional.empty());

        String json = """
                {
                  "username": "nouser",
                  "password": "password"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_invalidCredentials_returnsUnauthorized() throws Exception {
        Mockito.when(authenticationManager.authenticate(any()))
                .thenThrow(new RuntimeException("Bad credentials"));

        String json = """
                {
                  "username": "baduser",
                  "password": "badpassword"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized());
    }
}
