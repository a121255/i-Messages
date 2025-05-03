package com.test.imessagesbackend.room.repository;

import com.test.imessagesbackend.room.entity.RoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser, Long> {

}