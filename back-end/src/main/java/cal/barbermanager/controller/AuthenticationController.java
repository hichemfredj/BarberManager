package cal.barbermanager.controller;

import cal.barbermanager.dto.JwtLoginDTO;
import cal.barbermanager.dto.LoginDTO;
import cal.barbermanager.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    /**
     * Dependencies
     */
    private final AuthenticationService authenticationService;


    /**
     * Constructors
     */

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    /**
     * Services
     */

    @PostMapping("authenticate")
    public Object authenticate(@Valid @RequestBody LoginDTO loginDTO){

        JwtLoginDTO jwtLoginDTO = authenticationService.authenticate(loginDTO);

        return jwtLoginDTO.getToken() == null ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : jwtLoginDTO;

    }
}
