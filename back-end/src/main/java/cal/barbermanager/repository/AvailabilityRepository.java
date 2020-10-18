package cal.barbermanager.repository;

import cal.barbermanager.model.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface AvailabilityRepository extends MongoRepository<Availability, UUID> {

    Availability findByDayAndEmployer(String day, UUID employer);

}
