package com.example.demo.services.serviceImpl;

import com.example.demo.model.Exceptions.ErrorCodes;
import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.entities.Game;
import com.example.demo.model.entities.Player;
import com.example.demo.repository.GameRepository;
import com.example.demo.services.GameService;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Service
public class GameServiceImpl implements GameService {


    @Value("${exceptions.gameNotFound}")
    private String gameNotFound;

    @Autowired
    PlayerService playerService;

    @Autowired
    GameRepository gameRepository;

    @Override
    public Game startGame(String gameTitle, int gamePoints, Integer player1Id, Integer player2Id) throws GameException {

        try{

            int gameHalfpoints = gamePoints/2;
            Player firstPlayer = playerService.getPlayerById(player1Id);
            Player secondPlayer = playerService.getPlayerById(player2Id);

            playerService.deductPointsOnGameStart(player1Id, player2Id, gameHalfpoints);

            Game game = new Game(gameTitle, gamePoints, firstPlayer, secondPlayer);

            game = gameRepository.save(game);

            return game;

        }catch (GameException exception){
            throw exception;
        }

    }

    @Override
    public Game endGame(Integer gameId, Integer winnerId) throws GameException {

        try{
            //Get game
            Game game = gameRepository.findById(gameId).get();

            //Get Winner Player
            Player winner = playerService.getPlayerById(winnerId);

            //Add Point to the Winner
            playerService.addPoints(winnerId, game.getGamePoints());

            //Set Winner to the Game
            game.setWinner(winner);

            //Update the game record
            game = gameRepository.save(game);

            return game;


        }catch (EntityNotFoundException | NoSuchElementException exception){
            throw new GameException(ErrorCodes.NOT_FOUND, gameNotFound);
        }

    }
}
