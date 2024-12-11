package com.example.realjungleboard.service;

import com.example.realjungleboard.dto.AuthDto;
import com.example.realjungleboard.entity.UserEntity;
import com.example.realjungleboard.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void signUp(AuthDto authDTO) {

        String nickname = authDTO.getNickname();
        String password = authDTO.getPassword();
        String email = authDTO.getEmail();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity data = new UserEntity();

        data.setNickname(nickname);
        data.setEmail(email);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(data);
    }
}