package com.test.imessagesbackend.room.entity;

import com.test.imessagesbackend.common.entity.BaseEntity;
import com.test.imessagesbackend.message.entity.Message;
import com.test.imessagesbackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "room")
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    public Room(User creator, String name) {
        this.creator = creator;
        this.name = name;
    }
}










