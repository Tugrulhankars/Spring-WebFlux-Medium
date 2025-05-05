package org.example.reactiveuserapp.controller;

import org.example.reactiveuserapp.dto.UserDto;
import org.example.reactiveuserapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Mono<String> addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping("/update")
    public Mono<String> updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @PostMapping("/delete")
    public Mono<String> deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/findAll")
    public Flux<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }
}
