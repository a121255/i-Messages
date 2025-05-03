package com.test.imessagesbackend.room.controller;

import com.test.imessagesbackend.common.error.exception.RoomNotFoundException;
import com.test.imessagesbackend.common.response.ApiResponse;
import com.test.imessagesbackend.room.dto.RoomResponse;
import com.test.imessagesbackend.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@ResponseStatus(HttpStatus.OK)
public class RoomController {

    private final RoomService roomService;

    /*
    * 사용자가 속한 채팅방을 모두 조회
    * room id가 파라미터로 왔을 경우 조회 조건에 추가
    * */
    @GetMapping("/rooms")
    public ApiResponse<List<RoomResponse>> findAllRooms(@RequestParam(required = false) String search) {
        Long id = 1L;
        return ApiResponse.success(roomService.findRoomsByUserIdAndSearchKeyword(id, search));
    }

    /*
     * 특정 room id의 채팅방 조회
     * */
    @GetMapping("/rooms/{roomId}")
    public ApiResponse<RoomResponse> findRoomByUserIdAndRoomId(@PathVariable Long roomId){
        Long id = 1L;
        return ApiResponse.success(roomService.findRoomByUserIdAndRoomId(1L, roomId));
    }

    @PostMapping("/rooms")
    public ApiResponse<RoomResponse> createChatRoom(){
        String name = "고양이";

        return ApiResponse.success(roomService.createRoom(1L, name));
    }
}
