package cal.barbermanager.controller;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.service.AvailabalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    //
    // Dependencies
    //

    private final AvailabalityService availabalityService;

    //
    // Constructors
    //

    @Autowired
    public AvailabilityController(AvailabalityService availabalityService){
        this.availabalityService = availabalityService;
    }

    //
    // Post
    //

    @PreAuthorize("hasAuthority('EMPLOYER')")
    @PostMapping("create")
    public void createAvailability(@Valid @RequestBody AvailabilityCreation availabilityCreation){
        availabalityService.createAvailability(availabilityCreation);
    }
}
