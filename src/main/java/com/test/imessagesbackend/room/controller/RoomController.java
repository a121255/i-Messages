package com.test.imessagesbackend.room.controller;

import com.test.imessagesbackend.common.response.ApiResponse;
import com.test.imessagesbackend.common.util.AuthenticationUtil;
import com.test.imessagesbackend.room.dto.RoomResponse;
import com.test.imessagesbackend.room.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "채팅방", description = "채팅방 관련 API를 제공합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class RoomController {

    private final RoomService roomService;
    private final AuthenticationUtil authenticationUtil;

    /*
    * 사용자가 속한 채팅방을 모두 조회
    * room id가 파라미터로 왔을 경우 조회 조건에 추가
    * */
    @Operation(summary = "채팅방 목록 조회", description = "사용자가 속한 채팅방의 정보를 모두 조회합니다.")
    @GetMapping("/rooms")
    public ApiResponse<List<RoomResponse>> findAllRooms(@Parameter(description = "검색 키워드") @RequestParam(required = false) String search) {
        Long userId = authenticationUtil.getUserId();
        return ApiResponse.success(roomService.findRoomsByUserIdAndSearchKeyword(userId, search));
    }

    /*
     * 특정 room id의 채팅방 조회
     * */
    @Operation(summary = "특정 채팅방 조회", description = "사용자가 속한 특정 roomId의 채팅방 정보를 조회합니다.")
    @GetMapping("/rooms/{roomId}")
    public ApiResponse<RoomResponse> findRoomByUserIdAndRoomId(@Parameter(description = "조회할 roomId") @PathVariable Long roomId){
        Long userId = authenticationUtil.getUserId();
        return ApiResponse.success(roomService.findRoomByUserIdAndRoomId(userId, roomId));
    }

    @Operation(summary = "채팅방 생성", description = "채팅방을 생성합니다.")
    @PostMapping("/rooms")
    public ApiResponse<RoomResponse> createChatRoom(){
        String name = "고양이";
        Long userId = authenticationUtil.getUserId();
        return ApiResponse.success(roomService.createRoom(userId, name));
    }
}
