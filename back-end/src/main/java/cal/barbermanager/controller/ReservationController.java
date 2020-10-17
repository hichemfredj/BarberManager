package cal.barbermanager.controller;

import cal.barbermanager.dto.ReservationDTO;
import cal.barbermanager.model.Reservation;
import cal.barbermanager.model.User;
import cal.barbermanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {


    //
    // Dependencies
    //

    private final ReservationService reservationService;



    //
    // Constructors
    //

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    //
    // Post
    //
    @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/create-reservation")
    public void createReservation(@Valid @RequestBody ReservationDTO reservationDTO){

        reservationService.createReservation(reservationDTO);

    }

    //
    //  GET
    //


    @GetMapping("list-reservation")
    public List<Reservation> getListReservation(){
        return reservationService.getListReservation();
    }

    @GetMapping("list-reservation/barber/{barberId}")
    public List<Reservation> getReservationByBarberId(@PathVariable UUID barberId){

        return reservationService.getListReservationByBarberId(barberId);

    }
    @GetMapping("list-reservation/client/{clientId}")
    public List<Reservation> getReservationByClientId(@PathVariable UUID clientId){

        return reservationService.getListReservationByClientId(clientId);

    }

}
