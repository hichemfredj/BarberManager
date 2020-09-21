package cal.barbermanager.service;

import cal.barbermanager.dto.RegisterDTO;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void registerClient_validRequest() {

        // Arrange

        RegistrationService service = new RegistrationService(userRepository, passwordEncoder);

        RegisterDTO registerDTO = new RegisterDTO();

        registerDTO.setEmail("test@test.com");
        registerDTO.setFirstName("test");
        registerDTO.setLastName("test");
        registerDTO.setPassword("123456789");

        // Act & Assert

        Mockito.when(userRepository.save(Mockito.any())).then(inv -> {

            User user = (User) inv.getArgument(0);

            assertNotNull(user.getUniqueId());
            assertEquals(registerDTO.getEmail(), user.getEmail());
            assertEquals(registerDTO.getFirstName(), user.getFirstName());
            assertEquals(registerDTO.getLastName(), user.getLastName());
            assertEquals("CLIENT", user.getType());
            assertTrue(passwordEncoder.matches(registerDTO.getPassword(), user.getPasswordHash()));

            return null;
        });

        service.registerClient(registerDTO);

    }

}
