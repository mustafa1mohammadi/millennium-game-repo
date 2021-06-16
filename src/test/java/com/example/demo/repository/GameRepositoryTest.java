package com.example.demo.repository;

import com.example.demo.model.entities.Game;
import com.example.demo.model.entities.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerRepository playerRepository;

    private Game game;
    private Player firstPlayer;
    private Player secondPlayer;


    @BeforeEach
    public void setup(){
        firstPlayer = new Player("Mustafa", 10);
        secondPlayer = new Player("Ehsan", 10);
        firstPlayer =  playerRepository.save(firstPlayer);
        secondPlayer = playerRepository.save(secondPlayer);

        game = new Game("Early Birds", 6, firstPlayer, secondPlayer);
    }

    @Test
    public void saveGameTest(){
        gameRepository.save(game);
        Game fetchedGame = gameRepository.findById(game.getId()).get();
        assertEquals(6, fetchedGame.getGamePoints());
    }

    @Test
    public void getAllGamesTest(){
        Game secondGame = new Game("Night Owls", 10, secondPlayer, firstPlayer);
        gameRepository.save(game);
        gameRepository.save(secondGame);
        List<Game> allGames = gameRepository.findAll();
        assertEquals("Ehsan", game.getSecondPlayer().getName());
        assertEquals(10, secondGame.getGamePoints());
    }



    @AfterEach
    public void cleanUp(){
        gameRepository.deleteAll();
        firstPlayer = null;
        secondPlayer = null;
        game = null;
    }

}
