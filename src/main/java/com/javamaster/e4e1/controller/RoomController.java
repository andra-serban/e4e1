package com.javamaster.e4e1.controller;

import com.javamaster.e4e1.dtos.RegisterDto;
import com.javamaster.e4e1.dtos.RoomDto;
import com.javamaster.e4e1.service.RoomService;
import com.javamaster.e4e1.service.UserService;
import com.javamaster.e4e1.utils.SuccessDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/room")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    //v1/room/add
    @PostMapping("/add")
    @SneakyThrows
    public ResponseEntity<SuccessDto> addRoom(@RequestBody @Validated RoomDto roomDto) {
        roomService.addRoom(roomDto);
        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
    }

//    @DeleteMapping("")
//    @SneakyThrows
//    //v1/user/id
//    public ResponseEntity<SuccessDto> deleteRoom(@RequestParam Long userId) {
////        roomService.removeUser(userId);
//        return new ResponseEntity<>(new SuccessDto(), HttpStatus.OK);
//    }

}


