package com.test.imessagesbackend.chat.controller;

import com.test.imessagesbackend.message.dto.MessageRequest;
import com.test.imessagesbackend.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/chat")
    public ResponseEntity<String> greeting(@RequestBody MessageRequest message) throws Exception {
        log.info("Processing message for roomId: {}, content: {}",
                message.getRoomId(), message.getContent());

        messageService.saveMessage(message);

        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/chat/" + message.getRoomId(), message);
        log.info("Message sent to destination: /sub/chat/{}", message.getRoomId());
        return ResponseEntity.ok("메시지 전송 완료");
    }
}
