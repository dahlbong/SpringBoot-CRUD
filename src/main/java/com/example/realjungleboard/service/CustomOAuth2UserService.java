package com.example.realjungleboard.service;

import com.example.realjungleboard.dto.*;
import com.example.realjungleboard.entity.UserEntity;
import com.example.realjungleboard.entity.constEnum.Role;
import com.example.realjungleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String nickname = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByNickname(nickname);

        if (existData == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setNickname(nickname);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setRole(Role.ROLE_USER);

            userRepository.save(userEntity);

            UserDto userDto = new UserDto();
            userDto.setNickname(nickname);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole(Role.ROLE_USER.toString());

            return new CustomOAuth2User(userDto);
        } else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setNickname(nickname);

            userRepository.save(existData);

            UserDto userDto = new UserDto();
            userDto.setNickname(nickname);
            userDto.setName(oAuth2Response.getName());
            userDto.setRole(existData.getRole().toString());

            return new CustomOAuth2User(userDto);
        }
    }
}
