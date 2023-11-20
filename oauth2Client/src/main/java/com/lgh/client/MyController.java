package com.lgh.client;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MyController {

//    @GetMapping("/b")
//    public Date b() {
//        return new Date();
//    }

    @GetMapping("/a")
    public Authentication a() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
