package com.example.demo.controllers;

import com.example.demo.model.Exceptions.ErrorResponse;
import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.dto.PlayerDTO;
import com.example.demo.model.entities.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/getPlayers")
    public ResponseEntity<List<Player>> getPlayers(){
        try {

            List<Player> players = playerService.getPlayers();
            return new ResponseEntity<>(players, HttpStatus.OK);

        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/register")
    public ResponseEntity registerPlayer(@RequestBody PlayerDTO playerDTO){

        try{
            Player player = playerService.registerPlayer(playerDTO);
            return new ResponseEntity<>(player, HttpStatus.CREATED);

        }catch (GameException exception){
            ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getDescription());
            return new ResponseEntity(errorResponse, exception.getErrorCode().getStatusCode());
        }
    }

    @GetMapping("/getPoints/{id}")
    public ResponseEntity getTotalPoints (@PathVariable("id") Integer id){
       try{

           long totalPoints =  playerService.getTotalPoints(id);
           return new ResponseEntity (totalPoints, HttpStatus.OK);

       }catch (GameException exception){
           ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getDescription());
           return new ResponseEntity(errorResponse, exception.getErrorCode().getStatusCode());
       }
    }

}
