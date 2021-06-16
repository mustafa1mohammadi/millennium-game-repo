package com.example.demo.services.serviceImpl;

import com.example.demo.model.dto.PlayerDTO;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.model.Exceptions.ErrorCodes;
import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.entities.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Value("${exceptions.playerNotFound}")
    private String playerNotFound;

    @Value("${exceptions.playerNameError}")
    private String playerNameError;


    @Value("${exceptions.notEnoughPoints}")
    private String notEnoughPoints;



    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> getPlayers() {

        return playerRepository.findAll();
    }

    @Override
    public Player registerPlayer(PlayerDTO playerDTO) throws GameException {

        if(playerDTO.getName()==null || playerDTO.getName().trim().isEmpty()){
            throw new GameException(ErrorCodes.BAD_REQUEST, playerNameError);
        }

        Player player = new Player(playerDTO.getName(), playerDTO.getTotalPoints());
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player getPlayerById(Integer playerId) throws GameException {
        try{
            Player player = playerRepository.findById(playerId).get();

            return player;
        }catch (EntityNotFoundException | NoSuchElementException exception){
            throw new GameException(exception, ErrorCodes.NOT_FOUND, playerNotFound);
        }
    }

    @Override
    public long getTotalPoints(Integer playerId) throws GameException {
        try{
            Player player = playerRepository.findById(playerId).get();


            return player.getTotalPoints();
        }catch (EntityNotFoundException | NoSuchElementException exception){
            throw new GameException(exception, ErrorCodes.NOT_FOUND, playerNotFound);
        }
    }



    @Override
    public long addPoints(Integer playerId, long addablePoints) throws GameException {

        try{
            Player player = playerRepository.findById(playerId).get();
            player.setTotalPoints(player.getTotalPoints()+addablePoints);
            playerRepository.save(player);

            return player.getTotalPoints();

        }catch (EntityNotFoundException exception){
            throw new GameException(exception, ErrorCodes.NOT_FOUND, playerNotFound);
        }

    }

    @Override
    public long deductPointsOnGameStart(Integer player1Id, Integer player2Id, long deductablePoints) throws GameException {

        try{

            Player player1 = playerRepository.findById(player1Id).get();
            Player player2 = playerRepository.findById(player2Id).get();

            if(player1.getTotalPoints()<deductablePoints || player2.getTotalPoints()<deductablePoints){
                throw new GameException(ErrorCodes.BAD_REQUEST, notEnoughPoints);
            }

            player1.setTotalPoints(player1.getTotalPoints()- deductablePoints);
            player2.setTotalPoints(player2.getTotalPoints()- deductablePoints);
            playerRepository.save(player1);
            playerRepository.save(player2);

            return 1;

        }catch (EntityNotFoundException exception){

            throw new GameException(exception, ErrorCodes.NOT_FOUND, playerNotFound);
        }
    }
}
