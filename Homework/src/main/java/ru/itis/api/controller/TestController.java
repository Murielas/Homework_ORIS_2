package ru.itis.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.service.UserService;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String getUsersPage() {
        return null;
    }

    @GetMapping(path = "/users/v2")
    public String getUsersPageV2() {
        return "index";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id,
                              @RequestParam("age") Integer age) {
        return null;
    }
}
