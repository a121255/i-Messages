package com.test.imessagesbackend.message.service;

import com.test.imessagesbackend.common.error.exception.RoomNotFoundException;
import com.test.imessagesbackend.common.error.exception.UserNotFoundException;
import com.test.imessagesbackend.message.dto.MessageRequest;
import com.test.imessagesbackend.message.dto.MessageResponse;
import com.test.imessagesbackend.message.entity.Message;
import com.test.imessagesbackend.message.repository.MessageRepository;
import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.room.repository.RoomRepository;
import com.test.imessagesbackend.user.entity.User;
import com.test.imessagesbackend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public List<MessageResponse> findMessageByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId)
                .stream()
                .map(message -> MessageResponse.builder()
                        .roomId(message.getRoom().getId())
                        .id(message.getId())
                        .content(message.getContent())
                        .senderId(message.getUser().getId())
                        .createdAt(message.getCreatedAt())
                        .build())
                .toList();
    }

    @Transactional
    public void saveMessage(MessageRequest messageRequest) {
        Long userId = 1L;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Room room = roomRepository.findById(messageRequest.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(messageRequest.getRoomId()));

        Message message = Message.toEntity(messageRequest.getContent(), user, room);
        messageRepository.save(message);
    }
}
