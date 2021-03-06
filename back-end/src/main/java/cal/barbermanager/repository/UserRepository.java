package cal.barbermanager.repository;

import cal.barbermanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    List<User> findAllByType(String type);
    User findByUniqueId(UUID id);



}
