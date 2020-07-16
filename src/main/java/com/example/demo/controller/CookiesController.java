package com.example.demo.controller;

import com.example.demo.service.IdService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CookiesController {

    private final IdService idService;

    public CookiesController(final IdService idService) {
        this.idService = idService;
    }

    @GetMapping
    public String oauth(@CookieValue("_oauth2_proxy") final String oauth2) {

        return idService.getEmail(oauth2).getEmail();

    }

}
