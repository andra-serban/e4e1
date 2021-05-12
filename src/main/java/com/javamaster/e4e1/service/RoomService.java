package com.javamaster.e4e1.service;

import com.javamaster.e4e1.dtos.RegisterDto;
import com.javamaster.e4e1.dtos.RoomDto;
import com.javamaster.e4e1.model.Room;
import com.javamaster.e4e1.model.User;
import com.javamaster.e4e1.repository.RoomRepository;
import com.javamaster.e4e1.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @SneakyThrows
    public void addRoom(RoomDto roomDto) {
        Room room = Room.builder()
                .name(roomDto.getName())
                .subject(roomDto.getSubject())
                .shortDescription(roomDto.getShortDescription())
                .roomKey(roomDto.getRoomKey()).build();
        roomRepository.save(room);
    }
}
