package com.test.imessagesbackend.room.dto;

import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RoomResponse {
    @Schema(description = "채팅방 ID")
    private final Long id;

    @Schema(description = "채팅방 이름")
    private final String name;

    @Builder
    public RoomResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
