package com.example.demo.services;


import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.dto.PlayerDTO;
import com.example.demo.model.entities.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.services.serviceImpl.PlayerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Autowired
    @InjectMocks
    private PlayerServiceImpl playerService;
    private Player firstPlayer;
    private Player secondPlayer;


    @BeforeEach
    public void stageSetup(){

        firstPlayer = new Player(1, "Mustafa", 10);
        secondPlayer = new Player(2, "Ehsan", 20);

    }

    @Test
    public void registerPlayerTest() throws GameException {

        PlayerDTO firstPlayerDTO = new PlayerDTO("Mustafa", 10);
        when(playerRepository.save(any())).thenReturn(firstPlayer);
        playerService.registerPlayer(firstPlayerDTO);
        verify(playerRepository,times(1)).save(any());

    }
    @Test
    public void getPlayersTest(){
        List<Player> players =new ArrayList<>();
        players.add(firstPlayer);
        players.add(secondPlayer);
        when(playerRepository.findAll()).thenReturn(players);
        List<Player> playerList = playerService.getPlayers();
        assertEquals(2, playerList.size());
        assertEquals("Mustafa", playerList.get(0).getName());
    }

    @Test
    public void getTotalPointsTest() throws GameException {

        Optional<Player> firstPlayerOptional =  Optional.of(firstPlayer);
        when(playerRepository.findById(any())).thenReturn(firstPlayerOptional);
        long totalPoints = playerService.getTotalPoints(firstPlayer.getId());
        assertEquals(10, totalPoints);

    }

    @Test
    public void addPointsTest() throws GameException {
        long addablePoints = 10;
        Optional<Player> firstPlayerOptional =  Optional.of(firstPlayer);
        when(playerRepository.findById(any())).thenReturn(firstPlayerOptional);
        long totalPoints = playerService.addPoints(firstPlayer.getId(), addablePoints);
        assertEquals(20, totalPoints);

    }

    @Test
    public void deductPointsOnGameStartTest() throws GameException {
        long deductablePoints = 3;
        secondPlayer.setTotalPoints(9);
        Optional<Player> firstPlayerOptional =  Optional.of(firstPlayer);
        Optional<Player> secondPlayerOptional = Optional.of(secondPlayer);
        when(playerRepository.findById(firstPlayer.getId())).thenReturn(firstPlayerOptional);
        when(playerRepository.findById(secondPlayer.getId())).thenReturn(secondPlayerOptional);

        playerService.deductPointsOnGameStart(firstPlayer.getId(), secondPlayer.getId(), deductablePoints);
        assertEquals(7, firstPlayer.getTotalPoints());
        assertEquals(6, secondPlayer.getTotalPoints());

    }

    @Test
    public void getPlayerByIdTest() throws GameException {
        Optional<Player> firstPlayerOptional =  Optional.of(firstPlayer);
        when(playerRepository.findById(firstPlayer.getId())).thenReturn(firstPlayerOptional);

        Player player = playerService.getPlayerById(1);
        assertEquals("Mustafa", player.getName());
        assertNotEquals(3, player.getTotalPoints());
    }

    @AfterEach
    public void cleanUp(){
        playerRepository.deleteAll();
    }

}
