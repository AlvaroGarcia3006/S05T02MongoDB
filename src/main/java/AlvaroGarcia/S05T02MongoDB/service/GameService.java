package AlvaroGarcia.S05T02MongoDB.service;


import AlvaroGarcia.S05T02MongoDB.model.dto.GameDTO;
import java.util.List;

public interface GameService {

    GameDTO createGame(String playerId);
    void deleteGamesByPlayerId(String playerId);
    List<GameDTO> findGamesByPlayerId(String playerId);
    Long countGamesByPlayerId(String playerId);
    Long countGamesWonByPlayerId(String playerId);
}
