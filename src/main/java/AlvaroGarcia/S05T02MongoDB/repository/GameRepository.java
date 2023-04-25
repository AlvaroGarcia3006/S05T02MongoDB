package AlvaroGarcia.S05T02MongoDB.repository;

import AlvaroGarcia.S05T02MongoDB.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.bson.types.ObjectId;

public interface GameRepository extends MongoRepository<Game, ObjectId> {
    void deleteByPlayerId(ObjectId playerId);
    List<Game> findByPlayerId(ObjectId playerId);
    Long countByPlayerId(ObjectId playerId);
    Long countByPlayerIdAndScore(ObjectId playerId, Integer score);
}
