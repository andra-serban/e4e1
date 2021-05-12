package com.javamaster.e4e1.service;

import com.javamaster.e4e1.exception.InvalidGameException;
import com.javamaster.e4e1.exception.InvalidParamException;
import com.javamaster.e4e1.exception.NotFoundException;
import com.javamaster.e4e1.model.Game;
import com.javamaster.e4e1.model.GamePlay;
import com.javamaster.e4e1.model.GameStatus;
import com.javamaster.e4e1.model.User;
import com.javamaster.e4e1.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(User user) {
        Game game = new Game();
        game.setGameId(UUID.randomUUID().toString());
        game.setUser1(user);
        game.setStatus(GameStatus.NEW);
//        game.setRoom();
        GameStorage.getInstance().setGames(game);
        return game;
    }

    public Game connectToGame(User user2, String gameId) throws InvalidParamException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        if (game.getUser2() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        game.setUser2(user2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGames(game);
        return game;
    }

    public Game connectToRandomGame(User user2) throws NotFoundException {
        Game game = GameStorage.getInstance().getGames()
                .values().stream()
                .filter((it->it.getStatus().equals(GameStatus.NEW)))
                .findFirst().orElseThrow(()-> new NotFoundException("Game not found"));

        game.setUser2(user2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGames(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new InvalidGameException("Gama is already finished");
        }

        if (checkRoomKey(gamePlay, game)) {
            System.out.println("found");
        }
        return game;
    }

    private boolean checkRoomKey(GamePlay gamePlay, Game game) {
        return game.getRoom().getRoomKey() == gamePlay.getRoomKey();
    }
}
