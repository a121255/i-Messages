package com.test.imessagesbackend.message.dto;

import com.test.imessagesbackend.message.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    @Schema(description = "채팅방 ID")
    private final Long roomId;

    @Schema(description = "현재 접속한 사용자 ID (메시지 발신/수신 여부 비교용)")
    private final Long currentUserId;

    @Schema(description = "메시지 ID")
    private final Long id;

    @Schema(description = "메시지 내용")
    private final String content;

    @Schema(description = "메시지 발신자 ID")
    private final Long senderId;

    @Schema(description = "메시지 생성 시간")
    private final LocalDateTime createdAt;

    @Builder
    public MessageResponse(Long roomId, Long currentUserId, Long id, String content, Long senderId, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.currentUserId = currentUserId;
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.createdAt = createdAt;
    }

    public static MessageResponse toDto(Message message, Long currentUserId) {
        return MessageResponse.builder()
                .roomId(message.getRoom().getId())
                .currentUserId(currentUserId)
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getUser().getId())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
