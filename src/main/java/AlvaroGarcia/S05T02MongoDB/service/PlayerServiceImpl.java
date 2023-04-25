package AlvaroGarcia.S05T02MongoDB.service;

import AlvaroGarcia.S05T02MongoDB.model.Game;
import AlvaroGarcia.S05T02MongoDB.model.Player;
import AlvaroGarcia.S05T02MongoDB.model.dto.PlayerDTO;
import AlvaroGarcia.S05T02MongoDB.repository.GameRepository;
import AlvaroGarcia.S05T02MongoDB.repository.PlayerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);

        if (playerRepository.existsByName(player.getName())) {
            throw new IllegalArgumentException("Player name already exists");
        }
        player.setRegistrationDate(LocalDateTime.now());
        Player savedPlayer = playerRepository.save(player);
        return convertToDTO(savedPlayer);
    }

    @Override
    public PlayerDTO updatePlayerName(String id, String name) {
        if (playerRepository.existsByName(name)) {
            throw new IllegalArgumentException("Player name already exists");
        }
        Player player = playerRepository.findById(new ObjectId(id)).orElseThrow(() -> new IllegalArgumentException("Player not found"));
        player.setName(name);
        Player updatedPlayer = playerRepository.save(player);
        return convertToDTO(updatedPlayer);
    }

    @Override
    public List<PlayerDTO> findAllPlayersWithWinRate() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playerDTOs = new ArrayList<>();

        players.forEach(player -> {
            PlayerDTO playerDTO = convertToDTO(player);
            float winRate = calculateWinRate(player.getId().toHexString());
            playerDTO.setWinRate(winRate);
            playerDTOs.add(playerDTO);
        });
        return playerDTOs;
    }
    @Override
    public Double getAverageWinRate() {
        List<Player> players = playerRepository.findAll();
        double totalWinRate = 0.0;
        int playerCount = 0;

        for (Player player : players) {
            List<Game> games = gameRepository.findByPlayerId(player.getId());
            int wins = 0;
            int totalGames = games.size();

            for (Game game : games) {
                if (game.getScore() == 7) {
                    wins++;
                }
            }
            if (totalGames > 0) {
                totalWinRate += (double) wins / totalGames;
                playerCount++;
            }
        }
        if (playerCount > 0) {
            return totalWinRate / playerCount;
        } else {
            return (double) 0;
        }
    }
    @Override
    public PlayerDTO getLoserPlayer() {
        List<PlayerDTO> playerDTOS = this.findAllPlayersWithWinRate();

        return playerDTOS.stream()
                .min(Comparator.comparing(PlayerDTO::getWinRate))
                .orElse(null);
    }

    @Override
    public PlayerDTO getWinnerPlayer() {
        List<PlayerDTO> playerDTOS = this.findAllPlayersWithWinRate();

        return playerDTOS.stream()
                .max(Comparator.comparing(PlayerDTO::getWinRate))
                .orElse(null);
    }

    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        BeanUtils.copyProperties(player, playerDTO);
        playerDTO.setId(player.getId().toHexString());
        return playerDTO;
    }
    private float calculateWinRate(String playerId) {
        long totalGames = gameService.countGamesByPlayerId(playerId);

        if (totalGames == 0) {
            return 0;
        }
        long gamesWon = gameService.countGamesWonByPlayerId(playerId);

        return (float) gamesWon / totalGames * 100;
    }
}
