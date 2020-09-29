package cal.barbermanager.controller;

import cal.barbermanager.dto.RegisterDTO;
import cal.barbermanager.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    /**
     * Dependencies
     */

    private final RegistrationService registrationService;



    /**
     * Constructors
     */

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;

    }

    /**
     * Services
     */

    @PostMapping("client")
    public void registerClient(@Valid @RequestBody RegisterDTO registerDTO){
        registrationService.registerClient(registerDTO);
    }

    @PostMapping("employer")
    public void registerEmployer(@Valid @RequestBody RegisterDTO registerDTO){
        registrationService.registerEmployer(registerDTO);
    }




}
