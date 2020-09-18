package cal.barbermanager.service;

import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
public class RegistrationService {

    //
    // Dependencies
    //

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //
    // Constructors
    //

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //
    // Services
    //


}
