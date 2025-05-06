package com.test.imessagesbackend.user.entity;

import com.test.imessagesbackend.common.entity.BaseEntity;
import com.test.imessagesbackend.message.entity.Message;
import com.test.imessagesbackend.room.entity.Room;
import com.test.imessagesbackend.room.entity.RoomUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @OneToMany(mappedBy = "user")
    private List<RoomUser> roomUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Room> createdRooms = new ArrayList<>();

    @Builder
    public User(String password, String profileImgUrl) {
        this.password = password;
        this.profileImgUrl = profileImgUrl;
    }
}
