package com.example.demo.service;

import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class IdService {

    private static final String USER_INFO_PATH = "/oauth2/userinfo";

    private final RestTemplate restTemplate;
    private final URI url;

    public IdService(final RestTemplateBuilder builder, @Value("${auth.proxy.url}") final String authUrl) {

        this.url = URI.create(authUrl + USER_INFO_PATH);
        this.restTemplate = builder
                .build();
    }

    public UserInfo getEmail(final String oauth2) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "_oauth2_proxy=" + oauth2);
        RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, url);
        return restTemplate.exchange(requestEntity, UserInfo.class).getBody();
    }

}
