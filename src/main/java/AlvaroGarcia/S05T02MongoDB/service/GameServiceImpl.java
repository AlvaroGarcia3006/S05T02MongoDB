package AlvaroGarcia.S05T02MongoDB.service;

import AlvaroGarcia.S05T02MongoDB.model.Game;
import AlvaroGarcia.S05T02MongoDB.model.Player;
import AlvaroGarcia.S05T02MongoDB.model.dto.GameDTO;
import AlvaroGarcia.S05T02MongoDB.repository.GameRepository;
import AlvaroGarcia.S05T02MongoDB.repository.PlayerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class GameServiceImpl implements GameService{

    private static final Integer WINNER_SCORE = 7;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public GameDTO createGame(String playerId) {
        Player player = playerRepository.findById(new ObjectId(playerId))
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Game game = new Game();
        game.setPlayer(player);
        game.setDice1(rollDice());
        game.setDice2(rollDice());
        game.setScore(game.getDice1() + game.getDice2());
        Game savedGame = gameRepository.save(game);
        return convertToDTO(savedGame);
    }

    @Override
    public void deleteGamesByPlayerId(String playerId) {
        gameRepository.deleteByPlayerId(new ObjectId(playerId));
    }

    @Override
    public List<GameDTO> findGamesByPlayerId(String playerId) {
        List<Game> gameList = gameRepository.findByPlayerId(new ObjectId(playerId));
        List<GameDTO> gameDTOList = new ArrayList<>();
        gameList.forEach(game -> gameDTOList.add(this.convertToDTO(game)));
        return gameDTOList;
    }

    @Override
    public Long countGamesByPlayerId(String playerId) {
        return gameRepository.countByPlayerId(new ObjectId(playerId));
    }

    @Override
    public Long countGamesWonByPlayerId(String playerId) {
        return gameRepository.countByPlayerIdAndScore(new ObjectId(playerId), WINNER_SCORE);
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        BeanUtils.copyProperties(game, gameDTO);
        gameDTO.setPlayerId(game.getPlayer().getId().toHexString());
        return gameDTO;
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
