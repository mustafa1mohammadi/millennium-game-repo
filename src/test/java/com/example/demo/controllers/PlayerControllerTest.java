package com.example.demo.controllers;

import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.entities.Player;
import com.example.demo.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;
    private Player firstPlayer;
    private Player secondPlayer;
    private List<Player> playerList;

    @InjectMocks
    private PlayerController playerController;

    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
        firstPlayer = new Player(1,"Mustafa", 20);
        secondPlayer = new Player(2, "Ehsan", 10);
        playerList = new ArrayList<>();
        playerList.add(firstPlayer);
        playerList.add(secondPlayer);
    }

    @Test
    public void registerPlayerTest() throws Exception, GameException {
        when(playerService.registerPlayer(any())).thenReturn(firstPlayer);
        mockMvc.perform(post("/api/player/register").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(firstPlayer))).
                andExpect(status().isCreated());
        verify(playerService, times(1)).registerPlayer(any());
    }


    @Test
    public void GetMappingOfAllProduct() throws Exception {
        when(playerService.getPlayers()).thenReturn(playerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/player/getPlayers").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(firstPlayer))).
                andDo(MockMvcResultHandlers.print());
        verify(playerService).getPlayers();
        verify(playerService,times(1)).getPlayers();
    }

    @AfterEach
    public void tearDown() {
        firstPlayer = null;
        secondPlayer = null;
        playerList = null;
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
