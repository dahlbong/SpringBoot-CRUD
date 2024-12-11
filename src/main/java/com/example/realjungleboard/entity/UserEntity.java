package com.example.realjungleboard.entity;

import com.example.realjungleboard.entity.constEnum.AuthProvider;
import com.example.realjungleboard.entity.constEnum.Role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    private String nickname;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider = AuthProvider.LOCAL;    // GOOGLE, LOCAL

    private String providerId;        // 소셜 로그인의 경우 제공되는 ID
}
