package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.UserDto;
import org.java.training.helpdesk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Set<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(final HttpServletRequest request,
                                          @Valid @RequestBody final UserDto dto) throws URISyntaxException {
        System.out.println(dto.getEmail());
        UserDto created = userService.create(dto);
        return ResponseEntity.created(new URI(request.getRequestURI())).body(created);
    }

}