package com.financial.api.app.controllers;

import com.financial.api.app.requests.LoginRequest;
import com.financial.api.app.responses.LoginResponse;
import com.financial.api.app.utils.BCriptyUtil;
import com.financial.api.domain.user.exceptions.UserNotFoundException;
import com.financial.api.domain.user.service.IUserService;
import com.financial.api.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/api/v1")
public class LoginController {
    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping(value = "/auth")
    public Mono<ResponseEntity<LoginResponse>> authenticate(@RequestBody @Validated LoginRequest loginRequest) {
        return userService
                .findByUsername(loginRequest.username())
                .map(user -> {
                    if(!BCriptyUtil.checkPassword(loginRequest.password(), user.password())) {
                        throw new UserNotFoundException("Usuário não encontrado");
                    }
                    return ResponseEntity.ok().body(new LoginResponse(user.id(),jwtUtils.generateJwtToken(user.id())));
                });
    }

}
