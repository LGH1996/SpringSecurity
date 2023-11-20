package com.lgh.authorization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

//    @GetMapping("/b")
//    public Date b() {
//        return new Date();
//    }

    @GetMapping("/a")
    public Map<String, String> a() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lgh", "aaaaa");
        map.put("lin", "bbbb");
        return map;
    }
}
