package cal.barbermanager.service;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.model.Availability;
import cal.barbermanager.model.Reservation;
import cal.barbermanager.repository.AvailabilityRepository;
import cal.barbermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class AvailabalityService {

    //
    // Dependencies
    //

    private final AvailabilityRepository availabilityRepository;


    //
    // Constructors
    //

    @Autowired
    public AvailabalityService(AvailabilityRepository availabilityRepository){
        this.availabilityRepository = availabilityRepository;
    }

    //
    // Services
    //

    public void createAvailability(@Valid List<AvailabilityCreation> availabilityCreation){

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UUID user = UUID.fromString((String) authentication.getPrincipal());

        Availability availability = new Availability();

        for(AvailabilityCreation a : availabilityCreation){

            availability.setUniqueId(UUID.randomUUID());
            availability.setEmployer(user);
            availability.setDay(a.getDay());
            availability.setStartTime(a.getStartTime());
            availability.setEndTime(a.getEndTime());
            availability.setAvailable(a.isAvailable());

            availabilityRepository.save(availability);

        }


    }

    public Availability getAvailaibility(String date, UUID employer){
        Availability availability = availabilityRepository.findByDayAndEmployer(date, employer);
        return availability;
    }
}
