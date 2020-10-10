package cal.barbermanager.service;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.model.Availability;
import cal.barbermanager.repository.AvailabilityRepository;
import cal.barbermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
public class AvailabalityService {

    //
    // Dependencies
    //

    private final AvailabilityRepository availabilityRepository;

    private final UserRepository userRepository;

    //
    // Constructors
    //

    @Autowired
    public AvailabalityService(AvailabilityRepository availabilityRepository, UserRepository userRepository){
        this.availabilityRepository = availabilityRepository;
        this.userRepository = userRepository;
    }

    //
    // Services
    //

    public void createAvailability(@Valid AvailabilityCreation availabilityCreation){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UUID user = UUID.fromString((String) authentication.getPrincipal());

        Availability availability = new Availability();

        availability.setUniqueId(UUID.randomUUID());
        availability.setEmployer(user);
        availability.setDay(availabilityCreation.getDay());
        availability.setStartTime(availabilityCreation.getStartTime());
        availability.setEndTime(availabilityCreation.getEndTime());
        availability.setAvailable(availabilityCreation.isAvailable());


        availabilityRepository.save(availability);
    }
}
