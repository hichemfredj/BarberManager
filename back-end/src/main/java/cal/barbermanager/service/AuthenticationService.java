package cal.barbermanager.service;

import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import cal.barbermanager.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public class AuthenticationService {

    //
    // Dependencies
    //

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    //
    // Constructors
    //

    public AuthenticationService(
            UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }


}
