package com.example.demo.controllers;

import com.example.demo.model.Exceptions.ErrorCodes;
import com.example.demo.model.Exceptions.ErrorResponse;
import com.example.demo.model.Exceptions.GameException;
import com.example.demo.model.dto.GameEndDto;
import com.example.demo.model.dto.GameStartDto;
import com.example.demo.model.entities.Game;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/startGame")
    public ResponseEntity startGame(@RequestBody GameStartDto gameStartDto){

        try {
            Game game = gameService.startGame(gameStartDto.getGameTitle(), gameStartDto.getGamePoints(), gameStartDto.getFirstPlayer(), gameStartDto.getSecondPlayer());
            return new ResponseEntity(game, HttpStatus.CREATED);

        } catch (GameException exception) {

            ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getDescription());
            return new ResponseEntity(errorResponse, exception.getErrorCode().getStatusCode());

        } catch (Exception e){
            return new ResponseEntity(ErrorCodes.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/endGame")
    public ResponseEntity endGame (@RequestBody GameEndDto gameEndDto){
        try {
            Game game = gameService.endGame(gameEndDto.getGameId(), gameEndDto.getWinnerId());
            return new ResponseEntity(game, HttpStatus.OK);
        } catch (GameException exception) {

            ErrorResponse errorResponse = new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getDescription());
            return new ResponseEntity(errorResponse, exception.getErrorCode().getStatusCode());

        } catch (Exception e){
            return new ResponseEntity(ErrorCodes.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
