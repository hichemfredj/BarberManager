package cal.barbermanager.repository;

import cal.barbermanager.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, UUID> {

}
