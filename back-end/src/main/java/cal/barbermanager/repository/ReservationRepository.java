package cal.barbermanager.repository;

import cal.barbermanager.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, UUID> {

    List<Reservation> findByEmployer(UUID barberId);
    List<Reservation> findByClient(UUID clientId);

    List<Reservation> findByDateAndEmployer(Object any, Object any1);
}
