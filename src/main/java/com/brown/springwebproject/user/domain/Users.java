package com.brown.springwebproject.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column()
    private String googleId;

    @Column()
    private Timestamp lastLoginAt;

    @Builder
    public Users(String email, String googleId, Timestamp lastLoginAt){
        this.email = email;
        this.googleId = googleId;
        this.lastLoginAt = lastLoginAt;
    }

    public Users update(String googleId, Timestamp lastLoginAt) {
        this.googleId = googleId;
        this.lastLoginAt = lastLoginAt;
        return this;
    }
}
