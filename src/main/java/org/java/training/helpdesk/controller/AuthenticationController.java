package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.UserCredentialDto;
import org.java.training.helpdesk.dto.UserDto;
import org.java.training.helpdesk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody final UserCredentialDto credentialDto) {
        UserDto dto = userService.login(credentialDto);
        return ResponseEntity.ok(dto);
    }
}