package cal.barbermanager.service;

import cal.barbermanager.dto.JwtLoginDTO;
import cal.barbermanager.dto.LoginDTO;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import cal.barbermanager.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void authenticate_validrequest(){

        // Arrange

        AuthenticationService authenticationService = new AuthenticationService(userRepository, jwtProvider, passwordEncoder);

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail("test@test.com");
        loginDTO.setPassword("12345678");

        User user = new User();

        user.setUniqueId(UUID.randomUUID());
        user.setEmail(loginDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(loginDTO.getPassword()));

        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));

        // Act & Assert

        JwtLoginDTO jwtLoginDTO = authenticationService.authenticate(loginDTO);

        assertNotNull(jwtLoginDTO);

        assertEquals(user.getUniqueId(),jwtLoginDTO.getUserUniqueId());
        assertNotNull(jwtLoginDTO.getToken());

    }

    @Test
    public void authenticate_invalidRequest() {

        // Arrange

        AuthenticationService service = new AuthenticationService(userRepository, jwtProvider, passwordEncoder);

        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail("test@test.com");
        loginDTO.setPassword("123456789");

        User user = new User();

        user.setUniqueId(UUID.randomUUID());
        user.setEmail(loginDTO.getEmail());
        user.setPasswordHash(passwordEncoder.encode(
                "This is a different password then the request"));

        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));

        // Act & Assert

        JwtLoginDTO jwtLoginDTO = service.authenticate(loginDTO);

        assertNotNull(jwtLoginDTO);

        assertNull(jwtLoginDTO.getUserUniqueId());
        assertEquals(user.getType(), jwtLoginDTO.getUserType());
        assertNull(jwtLoginDTO.getToken());
    }



}
