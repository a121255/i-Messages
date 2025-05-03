package com.test.imessagesbackend.common.error.exception;

import com.test.imessagesbackend.common.error.code.ErrorCode;

public class RoomNotFoundException extends BaseException {
    public RoomNotFoundException(Long roomId) {
        super(ErrorCode.ROOM_NOT_FOUND, "Room not found with id: " + roomId);
    }

    public RoomNotFoundException(Long roomId, String message) {
        super(ErrorCode.ROOM_NOT_FOUND, message + ": " + roomId);
    }
}
