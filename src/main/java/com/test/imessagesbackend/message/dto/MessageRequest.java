package com.test.imessagesbackend.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MessageRequest {

    @Schema(description = "채팅방 ID")
    private Long roomId;

    @Schema(description = "메시지 내용")
    private String content;

}
