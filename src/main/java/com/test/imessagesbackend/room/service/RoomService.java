package com.test.imessagesbackend.room.service;

import com.test.imessagesbackend.common.error.exception.RoomNotFoundException;
import com.test.imessagesbackend.room.dto.RoomResponse;
import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.room.entity.RoomUser;
import com.test.imessagesbackend.room.repository.RoomRepository;
import com.test.imessagesbackend.room.repository.RoomUserRepository;
import com.test.imessagesbackend.user.entity.User;
import com.test.imessagesbackend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final UserRepository userRepository;

    public List<RoomResponse> findRoomsByUserIdAndSearchKeyword(Long userId, String search) {
        return roomRepository.findRoomsByUserIdAndRoomId(userId, search == null ? null : Long.parseLong(search))
                .stream()
                .map(room -> RoomResponse.builder()
                        .id(room.getId())
                        .name(room.getName())
                        .creator(room.getCreator().getUserId())
                        .roomUsers(room.getRoomUsers().stream()
                                .map(roomUser -> roomUser.getUser().getId())
                                .collect(Collectors.toList()))
                        .build())
                .toList();
    }

    public RoomResponse findRoomByUserIdAndRoomId(Long userId, Long roomId) {
        return roomRepository.findByUserIdAndRoomId(userId, roomId)
                .map(room -> {
                    List<Long> roomUserIds = room.getRoomUsers().stream()
                            .map(roomUser -> roomUser.getUser().getId())
                            .collect(Collectors.toList());

                    return RoomResponse.builder()
                            .id(room.getId())
                            .name(room.getName())
                            .creator(room.getCreator().getUserId())
                            .roomUsers(roomUserIds)
                            .build();
                })
                .orElseThrow(() -> new RoomNotFoundException(roomId, "채팅방을 찾을 수 없습니다"));
    }

    @Transactional
    public RoomResponse createRoom(Long userId, String roomName) {
        // 생성자 정보로 Room 생성 (생성자 패턴 사용)
        User creator = userRepository.findById(userId).get();
        Room room = new Room(creator, roomName);

        // 저장하여 ID 생성
        Room savedRoom = roomRepository.save(room);

        // 생성자를 첫 참여자로 추가 (생성자 패턴 사용)
        RoomUser roomUser = new RoomUser(savedRoom, creator, LocalDateTime.now());
        roomUserRepository.save(roomUser);

        List<Long> roomUserIds = savedRoom.getRoomUsers().stream()
                .map(user -> user.getUser().getId())
                .collect(Collectors.toList());

        return RoomResponse.builder()
                .id(savedRoom.getId())
                .name(savedRoom.getName())
                .creator(savedRoom.getCreator().getUserId())
                .roomUsers(roomUserIds)
                .build();
    }

}
