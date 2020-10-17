package cal.barbermanager.service;

import cal.barbermanager.dto.ReservationDTO;
import cal.barbermanager.model.Reservation;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Validated
public class ReservationService {

    //
    //Dependencies
    //

    private final ReservationRepository reservationRepository;

    //
    // Constructors
    //
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    //
    //Services
    //

    public void createReservation(@Valid ReservationDTO reservationDTO){

        Reservation reservation = new Reservation();

            reservation.setUniqueId(UUID.randomUUID());
            reservation.setClient(reservationDTO.getClient());
            reservation.setEmployer(reservationDTO.getEmployer());
            reservation.setDate(reservationDTO.getDate());
            reservation.setTime(reservationDTO.getTime());
            reservation.setClientName(reservationDTO.getClientName());
            reservation.setBarberName(reservationDTO.getBarberName());

            reservationRepository.save(reservation);

    }

    public List<ReservationDTO> getListReservation(){

        List<Reservation> listReservation = reservationRepository.findAll();

        List<ReservationDTO> listReservationDTO = new ArrayList<ReservationDTO>();

        ReservationDTO reservationDTO = new ReservationDTO();

        for(Reservation reservation : listReservation){
            reservationDTO.setBarberName(reservation.getBarberName());
            reservationDTO.setClientName(reservation.getClientName());
            reservationDTO.setTime(reservation.getTime());
            reservationDTO.setDate(reservation.getDate());

            listReservationDTO.add(reservationDTO);
        }

        return listReservationDTO;
    }

    public List<ReservationDTO> getListReservationByBarberName(String barberName){

        List<Reservation> listReservation = reservationRepository.findByBarberName(barberName);

        List<ReservationDTO> listReservationDTO = new ArrayList<ReservationDTO>();

        ReservationDTO reservationDTO = new ReservationDTO();

        for(Reservation reservation : listReservation){
            reservationDTO.setBarberName(reservation.getBarberName());
            reservationDTO.setClientName(reservation.getClientName());
            reservationDTO.setTime(reservation.getTime());
            reservationDTO.setDate(reservation.getDate());

            listReservationDTO.add(reservationDTO);
        }

        return listReservationDTO;

    }
}
