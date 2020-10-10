package cal.barbermanager.service;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.model.Availability;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.AvailabilityRepository;
import cal.barbermanager.repository.UserRepository;
import cal.barbermanager.security.JwtAuthentication;
import cal.barbermanager.security.JwtProvider;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AvailabilityServiceTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createAvailability_validRequest(){

        // Arrange

        User user = new User();

        user.setUniqueId(UUID.randomUUID());
        user.setType("EMPLOYER");

        String token = jwtProvider.generate(user);
        DecodedJWT decodedToken = jwtProvider.verify(token);
        JwtAuthentication authentication = new JwtAuthentication(decodedToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AvailabalityService availabalityService = new AvailabalityService(availabilityRepository, null);

        AvailabilityCreation availabilityCreation = new AvailabilityCreation();

        availabilityCreation.setDay("Monday");
        availabilityCreation.setStartTime("10:00");
        availabilityCreation.setEndTime("20:00");
        availabilityCreation.setAvailable(true);


        // Act & assert

        Mockito.when(availabilityRepository.save(Mockito.any())).then(inv -> {

            Availability availability = (Availability) inv.getArgument(0);

            assertNotNull(availability.getUniqueId());
            assertEquals(user.getUniqueId(), availability.getEmployer());
            assertEquals(true,availability.isAvailable());
            assertEquals(availabilityCreation.getDay(),availability.getDay());
            assertEquals(availabilityCreation.getStartTime(),availability.getStartTime());
            assertEquals(availabilityCreation.getEndTime(),availability.getEndTime());

            System.out.println(user.getUniqueId());
            System.out.println(availability.getEmployer());

            System.out.println(availability);

            return null;

        });

        availabalityService.createAvailability(availabilityCreation);
    }
}
