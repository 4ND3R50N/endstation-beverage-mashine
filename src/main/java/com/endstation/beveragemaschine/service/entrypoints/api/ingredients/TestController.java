package com.endstation.beveragemaschine.service.entrypoints.api.ingredients;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/t")
    public String test() {
        return "Hi";
    }
}
