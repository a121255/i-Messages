package com.test.imessagesbackend.message.entity;

import com.test.imessagesbackend.common.entity.BaseEntity;
import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Builder
    private Message(String content, User user, Room room) {
        this.content = content;
        this.user = user;
        this.room = room;
    }

    public static Message toEntity(String content, User user, Room room) {
        return Message.builder()
                .content(content)
                .user(user)
                .room(room)
                .build();
    }
}