package com.example.realjungleboard.dto;

public interface OAuth2Response {

    String getProvider();
    //제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    String getEmail();
    String getName();

}
