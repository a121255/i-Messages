package com.test.imessagesbackend.message.controller;

import com.test.imessagesbackend.common.response.ApiResponse;
import com.test.imessagesbackend.message.dto.MessageResponse;
import com.test.imessagesbackend.message.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메시지", description = "채팅방 메시지 정보 관련 API를 제공합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@ResponseStatus(HttpStatus.OK)
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "특정 채팅방의 메시지 조회", description = "사용자가 속한 채팅방의 메시지를 조회합니다.")
    @GetMapping("/rooms/{roomId}/messages")
    public ApiResponse<List<MessageResponse>> getMessages(@Parameter(description = "조회할 roomId") @PathVariable Long roomId) {
        Long userId = 1L;   // 로그인 구현 전까지 하드코딩
        return ApiResponse.success(messageService.findMessageByRoomId(roomId));
    }
}
