package com.test.imessagesbackend.room.repository;

import com.test.imessagesbackend.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT ru.room FROM RoomUser ru WHERE ru.user.id = :userId " +
            "AND (:search IS NULL OR ru.room.id = :search)")
    List<Room> findRoomsByUserIdAndRoomId(Long userId, Long search);

    Optional<Room> findById(Long roomId);

    @Query("SELECT r FROM Room r JOIN r.roomUsers ru WHERE r.id = :roomId AND ru.user.id = :userId")
    Optional<Room> findByUserIdAndRoomId(Long userId, Long roomId);


}
