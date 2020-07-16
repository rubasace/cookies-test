package com.example.demo.service;

import com.example.demo.model.UserInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class IdService {

    private final RestTemplate restTemplate;
    private final URI url;

    public IdService(final RestTemplateBuilder builder) {

        this.url = URI.create("https://auth.nasvigo.com/oauth2/userinfo");
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
