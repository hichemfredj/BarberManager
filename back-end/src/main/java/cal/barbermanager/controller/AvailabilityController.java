package cal.barbermanager.controller;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.model.Availability;
import cal.barbermanager.model.Reservation;
import cal.barbermanager.service.AvailabalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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
    public void createAvailability(@Valid @RequestBody List<AvailabilityCreation> availabilityCreation){
        availabalityService.createAvailability(availabilityCreation);
    }
    @GetMapping("disponible/{day}/{employer}")
    public Availability getAvailability(@PathVariable String day, @PathVariable UUID employer){
        return availabalityService.getAvailaibility(day,employer);
    }
}
