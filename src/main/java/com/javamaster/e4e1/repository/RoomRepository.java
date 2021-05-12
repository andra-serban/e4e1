package com.javamaster.e4e1.repository;

import com.javamaster.e4e1.model.Room;
import com.javamaster.e4e1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
//    @Query("SELECT r FROM Room r WHERE r.id = :id")
//    Optional<Room> findRoomsById(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.name = :name")
    Optional<Room> findRoomsByName(@Param("name") String name);

}
