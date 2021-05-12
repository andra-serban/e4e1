package com.javamaster.e4e1.model;

import lombok.Data;

@Data
public class Game {
    private String gameId;
    private User user1;
    private User user2;
    private GameStatus status;
    private Room room;

}
