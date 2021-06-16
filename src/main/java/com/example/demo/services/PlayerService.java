package com.example.demo.services;

import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.dto.PlayerDTO;
import com.example.demo.model.entities.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers();
    Player registerPlayer(PlayerDTO playerDTO) throws GameException;
    Player getPlayerById (Integer playerId) throws GameException;
    long getTotalPoints(Integer playerId) throws GameException;
    long addPoints(Integer playerId, long addablePoints) throws GameException;
    long deductPointsOnGameStart(Integer player1Id, Integer Player2Id, long deductablePoints) throws GameException;
}
