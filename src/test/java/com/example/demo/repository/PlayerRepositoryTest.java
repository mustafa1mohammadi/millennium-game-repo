package com.example.demo.repository;

import com.example.demo.model.entities.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlayerRepositoryTest {


    @Autowired
    private PlayerRepository playerRepository;
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Player1", 10);
    }

    @Test
    public void savePlayerTest(){
        playerRepository.save(player);
        Player fetchedPlayer = playerRepository.findById(player.getId()).get();
        assertNotNull(fetchedPlayer);
        assertEquals("Player1", fetchedPlayer.getName());
        assertEquals(10, fetchedPlayer.getTotalPoints());
    }

    @Test
    public void getAllPlayersTest(){

        Player mustafa = new Player("Mustafa", 10);
        Player ehsan = new Player("Ehsan", 20);
        playerRepository.save(mustafa);
        playerRepository.save(ehsan);

        List<Player> allPlayers = playerRepository.findAll();
        assertEquals("Mustafa", allPlayers.get(0).getName());
        assertEquals(20, allPlayers.get(1).getTotalPoints());
    }

    @Test
    public void getPlayerByIdTest() {

        Player player = new Player("Mustafa", 20);
        Player savedPlayer = playerRepository.save(player);

        Player toBeTestedPlayer = playerRepository.findById(savedPlayer.getId()).get();
        assertEquals("Mustafa", toBeTestedPlayer.getName());
        assertEquals(20, toBeTestedPlayer.getTotalPoints());
    }


    @AfterEach
    public void cleanUp() {
        playerRepository.deleteAll();
        player = null;
    }
}
