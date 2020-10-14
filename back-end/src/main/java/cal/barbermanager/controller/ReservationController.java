package cal.barbermanager.controller;

import cal.barbermanager.dto.ReservationDTO;
import cal.barbermanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
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
    @PostMapping("create-reservation")
    public void createReservation(@Valid @RequestBody List<ReservationDTO> reservationDTO){

        reservationService.createReservation(reservationDTO);

    }

}
