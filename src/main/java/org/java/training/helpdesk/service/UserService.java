package org.java.training.helpdesk.service;

import org.java.training.helpdesk.converter.UserConverter;
import org.java.training.helpdesk.dto.UserCredentialDto;
import org.java.training.helpdesk.entity.enums.Role;
import org.java.training.helpdesk.repository.UserRepository;
import org.java.training.helpdesk.dto.ActionDto;
import org.java.training.helpdesk.dto.UserDto;
import org.java.training.helpdesk.entity.User;
import org.java.training.helpdesk.entity.enums.State;
import org.java.training.helpdesk.exception.NotFoundException;
import org.java.training.helpdesk.exception.NotUniqueException;
import org.java.training.helpdesk.exception.StateException;
import org.java.training.helpdesk.repository.impl.UserRepositoryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    private static final String MESSAGE = "Hello, %s! Please use this code to activate your profile : %s";
    private static final String BLOCK = "BLOCK";

    private final UserRepositoryImpl userRepository;
    private final UserConverter userConverter;
    private final AuthenticationService authenticationService;

    public UserService(UserRepositoryImpl userRepository, UserConverter userConverter, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.authenticationService = authenticationService;
    }

    public UserDto getUser(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException(User.class, email));
        return userConverter.toDto(user);
    }

    public Set<UserDto> getUsers() {
        return userRepository.findAll()
            .stream()
            .map(userConverter::toDto)
            .collect(Collectors.toSet());
    }

    @Transactional
    public UserDto login(UserCredentialDto credentialDto) {
        User user = userRepository.findUserByEmail(credentialDto.getEmail()).orElseThrow(() -> new NotFoundException(User.class, credentialDto.getEmail()));
        UserDto dto = userConverter.toDto(user);
        dto.setJwt(authenticationService.getToken(credentialDto));
        return dto;
    }

    @Transactional
    public UserDto create(UserDto dto) {
        dto.setEmail(dto.getFirstName() + dto.getLastName() +"@.com");
        userRepository.findUserByEmail(dto.getEmail()).ifPresent(s -> {throw new NotUniqueException();});
        User user = userConverter.fromDto(dto);
        User created = userRepository.save(user);
        return userConverter.toDto(created);
    }
}