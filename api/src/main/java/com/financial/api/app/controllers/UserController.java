package com.financial.api.app.controllers;

import com.financial.api.app.mappers.UserResponseMapper;
import com.financial.api.app.responses.UserResponse;
import com.financial.api.domain.user.filter.UserFilter;
import com.financial.api.domain.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<UserResponse>> getById(@PathVariable String id) {
        return userService.findById(id).map(user -> ResponseEntity.ok().body(UserResponseMapper.toUserFromUserResponse(user)));
    }

    @GetMapping
    public Mono<ResponseEntity<?>> getAll(@RequestParam(name = "username", required = false) String username) {
        List<UserResponse> responseList = new ArrayList<>();
        return userService.findAll((new UserFilter()).formatParams(username))
                .map(user -> responseList.add(UserResponseMapper.toUserFromUserResponse(user)))
                .then().thenReturn(ResponseEntity.ok().body(responseList));
    }

}
