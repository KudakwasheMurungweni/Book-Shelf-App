package zw.co.BookShelf.BookApp.Controller;

import zw.co.BookShelf.BookApp.dto.UserDto.UserLoginDto;
import zw.co.BookShelf.BookApp.dto.UserDto.UserLoginResponseDto;
import zw.co.BookShelf.BookApp.security.JwtTokenUtil;
import zw.co.BookShelf.BookApp.entity.User;
import zw.co.BookShelf.BookApp.Repository.UserRepository;

import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginDto loginDto) {
        logger.info("Login attempt for username: {}", loginDto.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenUtil.generateToken(loginDto.getUsername());
            User user = userRepository.findByUserName(loginDto.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            logger.info("Login successful for username: {}", loginDto.getUsername());
            String role = user.getRole().name(); // Use enum name

            return ResponseEntity.ok(
                    new UserLoginResponseDto(token, user.getUsername(), role)
            );
        } catch (Exception e) {
            logger.warn("Login failed for username: {} - {}", loginDto.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
