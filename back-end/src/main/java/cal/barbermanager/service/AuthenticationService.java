package cal.barbermanager.service;

import cal.barbermanager.dto.JwtLoginDTO;
import cal.barbermanager.dto.LoginDTO;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.UserRepository;
import cal.barbermanager.security.JwtProvider;
import lombok.extern.java.Log;
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


    //
    // Service
    //

    public JwtLoginDTO authenticate(@Valid LoginDTO loginDTO){

        JwtLoginDTO jwtLoginDTO = new JwtLoginDTO();

        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        if(user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPasswordHash())){
            String token = jwtProvider.generate(user.get());
            jwtLoginDTO.setUserUniqueId(user.get().getUniqueId());
            jwtLoginDTO.setToken(token);

        }

        return jwtLoginDTO;
    }


}
