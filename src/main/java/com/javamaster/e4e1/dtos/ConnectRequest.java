package com.javamaster.e4e1.dtos;

import com.javamaster.e4e1.model.User;
import lombok.Data;

@Data
public class ConnectRequest {
    private User user;
    private String gameId;
}
