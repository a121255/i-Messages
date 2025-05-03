package com.test.imessagesbackend.room.dto;

import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoomResponse {
    private final Long id;
    private final String name;
    private final String creator;
    private final List<Long> roomUsers;

    @Builder
    public RoomResponse(Long id, String name, String creator, List<Long> roomUsers) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.roomUsers = roomUsers;
    }
}
