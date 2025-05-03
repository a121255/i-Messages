package com.test.imessagesbackend.room.entity;

import com.test.imessagesbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime joinedAt;

    public RoomUser(Room room, User user, LocalDateTime joinedAt) {
        this.room = room;
        this.user = user;
        this.joinedAt = joinedAt;
    }

}
