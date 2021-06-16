package com.example.demo.services;

import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.entities.Game;

public interface GameService {

    Game startGame(String gameTitle, int gamePoints, Integer player1Id, Integer player2Id) throws GameException;
    Game endGame(Integer gameId, Integer winnerId) throws GameException;
}
