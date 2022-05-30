package com.financial.api.app.controllers;

import com.financial.api.app.mappers.UserResponseMapper;
import com.financial.api.app.responses.UserResponse;
import com.financial.api.domain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<UserResponse>> getById(@PathVariable String id) {
        return userService.findById(id).map(user -> ResponseEntity.ok().body(UserResponseMapper.toUserFromUserResponse(user)));
    }

}
