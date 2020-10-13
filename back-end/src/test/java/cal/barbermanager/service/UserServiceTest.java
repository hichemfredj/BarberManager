package cal.barbermanager.service;

import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void listEmployee_validRequest(){

        //Arrange

        User user = new User();

        user.setUniqueId(UUID.randomUUID());
        user.setType("EMPLOYER");
        user.setFirstName("Carlos");
        user.setLastName("Barber");



        UserService userService = new UserService(userRepository);


        // ACT & ASSERT

        Mockito.when(userRepository.findAllByType(Mockito.any())).thenReturn(List.of(user));

        assertEquals(List.of(user), userService.getListEmployer());



    }


}
