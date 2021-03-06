package com.javamaster.e4e1.controller;

import com.javamaster.e4e1.dtos.ConnectRequest;
import com.javamaster.e4e1.exception.InvalidGameException;
import com.javamaster.e4e1.exception.InvalidParamException;
import com.javamaster.e4e1.exception.NotFoundException;
import com.javamaster.e4e1.model.Game;
import com.javamaster.e4e1.model.GamePlay;
import com.javamaster.e4e1.model.User;
import com.javamaster.e4e1.service.GameService;
import com.javamaster.e4e1.storage.GameStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody User user) {
        log.info("start game request: {}", user);
        return ResponseEntity.ok(gameService.createGame(user));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getUser(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody User user) throws NotFoundException {
        log.info("connect random: {}", user);
        return ResponseEntity.ok(gameService.connectToRandomGame((user)));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws NotFoundException, InvalidGameException {
        log.info("gameplay: {}", request);
        Game game = gameService.gamePlay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameId(), game);
        return ResponseEntity.ok(game);
    }

}
