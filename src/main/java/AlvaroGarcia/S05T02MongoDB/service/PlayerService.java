package AlvaroGarcia.S05T02MongoDB.service;

import AlvaroGarcia.S05T02MongoDB.model.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO updatePlayerName(String id, String name);
    List<PlayerDTO> findAllPlayersWithWinRate();
    Double getAverageWinRate();
    PlayerDTO getLoserPlayer();
    PlayerDTO getWinnerPlayer();
}
