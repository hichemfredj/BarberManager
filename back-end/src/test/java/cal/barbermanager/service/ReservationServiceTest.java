package cal.barbermanager.service;

import cal.barbermanager.dto.AvailabilityCreation;
import cal.barbermanager.dto.ReservationDTO;
import cal.barbermanager.model.Availability;
import cal.barbermanager.model.Reservation;
import cal.barbermanager.model.User;
import cal.barbermanager.repository.AvailabilityRepository;
import cal.barbermanager.repository.ReservationRepository;
import cal.barbermanager.repository.UserRepository;
import cal.barbermanager.security.JwtAuthentication;
import cal.barbermanager.security.JwtProvider;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Mock
    private ReservationRepository reservationRepository;


    @Test
    public void createReservation_validRequest() {

        // Arrange

        User user = new User();

        user.setUniqueId(UUID.randomUUID());
        user.setType("CLIENT");

        String token = jwtProvider.generate(user);
        DecodedJWT decodedToken = jwtProvider.verify(token);
        JwtAuthentication authentication = new JwtAuthentication(decodedToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ReservationService reservationService = new ReservationService(reservationRepository);

        ReservationDTO r = new ReservationDTO();

        r.setEmployer(UUID.randomUUID());
        r.setClient(UUID.randomUUID());
        r.setClientName("Hichem");
        r.setBarberName("Carlos");
        r.setDate("28/10/2020");
        r.setTime("11:00AM");


        // Act & assert

        Mockito.when(reservationRepository.save(Mockito.any())).then(inv -> {

            Reservation reservation = (Reservation) inv.getArgument(0);

            assertNotNull(reservation.getUniqueId());
            assertEquals(r.getClient(), reservation.getClient());
            assertEquals(r.getEmployer(),reservation.getEmployer());
            assertEquals(r.getClientName(),reservation.getClientName());
            assertEquals(r.getBarberName(), reservation.getBarberName());
            assertEquals(r.getTime(), reservation.getTime());
            assertEquals(r.getDate(), reservation.getDate());


            System.out.println(reservation);

            return null;

        });

        reservationService.createReservation(r);
    }

    @Test
    public void listReservation_validRequest() {

        //Arrange

        Reservation reservation = new Reservation();

        reservation.setUniqueId(UUID.randomUUID());
        reservation.setClient(UUID.randomUUID());
        reservation.setEmployer(UUID.randomUUID());
        reservation.setBarberName("Carlos");
        reservation.setClientName("Hichem");
        reservation.setDate("15/10/2020");
        reservation.setTime("12:00");


        ReservationService reservationService = new ReservationService(reservationRepository);


        // ACT & ASSERT

        Mockito.when(reservationRepository.findAll()).thenReturn(List.of(reservation));

       // assertEquals(List.of(reservation), reservationService.getListReservation());

        assertNotNull(reservationService.getListReservation());


    }

    @Test
    public void listReservationBarberId_validRequest() {

        //Arrange

        Reservation reservation = new Reservation();

        reservation.setUniqueId(UUID.randomUUID());
        reservation.setClient(UUID.randomUUID());
        reservation.setEmployer(UUID.randomUUID());
        reservation.setBarberName("Carlos");
        reservation.setClientName("Hichem");
        reservation.setDate("15/10/2020");
        reservation.setTime("12:00");


        ReservationService reservationService = new ReservationService(reservationRepository);


        // ACT & ASSERT

        Mockito.when(reservationRepository.findByEmployer(Mockito.any())).thenReturn(List.of(reservation));

        // assertEquals(List.of(reservation), reservationService.getListReservation());

        assertNotNull(reservationService.getListReservationByBarberId(reservation.getEmployer()));


    }
    @Test
    public void listReservationClientId_validRequest() {

        //Arrange

        Reservation reservation = new Reservation();

        reservation.setUniqueId(UUID.randomUUID());
        reservation.setClient(UUID.randomUUID());
        reservation.setEmployer(UUID.randomUUID());
        reservation.setBarberName("Carlos");
        reservation.setClientName("Hichem");
        reservation.setDate("15/10/2020");
        reservation.setTime("12:00");


        ReservationService reservationService = new ReservationService(reservationRepository);


        // ACT & ASSERT

        Mockito.when(reservationRepository.findByClient(Mockito.any())).thenReturn(List.of(reservation));


        assertNotNull(reservationService.getListReservationByClientId(reservation.getClient()));


    }

    @Test
    public void listReservationDate_validRequest() {

        //Arrange

        Reservation reservation = new Reservation();

        reservation.setUniqueId(UUID.randomUUID());
        reservation.setClient(UUID.randomUUID());
        reservation.setEmployer(UUID.randomUUID());
        reservation.setBarberName("Carlos");
        reservation.setClientName("Hichem");
        reservation.setDate("2020/10/18");
        reservation.setTime("12:00");


        ReservationService reservationService = new ReservationService(reservationRepository);


        // ACT & ASSERT

        Mockito.when(reservationRepository.findByDateAndEmployer(Mockito.any(), Mockito.any())).thenReturn(List.of(reservation));


        assertNotNull(reservationService.getListReservationByDateAndEmployer(reservation.getDate(), reservation.getEmployer()));


    }

}
