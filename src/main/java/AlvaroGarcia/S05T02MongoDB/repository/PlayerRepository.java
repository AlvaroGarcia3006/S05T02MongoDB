package AlvaroGarcia.S05T02MongoDB.repository;

import AlvaroGarcia.S05T02MongoDB.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import org.bson.types.ObjectId;

public interface PlayerRepository extends MongoRepository<Player, ObjectId> {
    boolean existsByName(String name);
    Optional<Player> findById(ObjectId id);
}
