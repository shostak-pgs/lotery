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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {
    private static final String MESSAGE = "Hello, %s! Please use this code to activate your profile : %s";
    private static final String BLOCK = "BLOCK";

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final AuthenticationService authenticationService;
    private final MailService mailService;

    public UserService(UserRepository userRepository, UserConverter userConverter, AuthenticationService authenticationService, MailService mailService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.authenticationService = authenticationService;
        this.mailService = mailService;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        return userConverter.toDto(user);
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

    @Transactional(noRollbackFor = StateException.class)
    public Set<UserDto> updateUsers(ActionDto dto) {
        Optional<State> state = State.fromAction(dto.getAction());
        Optional<Role> role = Role.fromAction(dto.getAction());
        dto.getSelected()
                .forEach((key, value) -> userRepository.findById(key).ifPresent(user -> {
                    role.ifPresent(user::setRole);
                    state.ifPresent(user::setState);
                    userRepository.save(user);
                }));
        blockIfNecessary(dto);
        return getUsers();
    }

    private void blockIfNecessary(ActionDto dto) {
        if (dto.getAction().equals(BLOCK)) {
            if (authenticationService.logoutIfBlocked(dto.getSelected().keySet())) {
                throw new StateException("User was blocked");
            }
        }
    }

    @Transactional
    public UserDto login(UserCredentialDto credentialDto) {
        User user = setActive(authenticationService.authenticate(credentialDto));
        UserDto dto = userConverter.toDto(user);
        dto.setJwt(authenticationService.getToken(credentialDto));
        return dto;
    }

    @Transactional
    public UserDto create(UserDto dto) {
        userRepository.findUserByEmail(dto.getEmail()).ifPresent(s -> {throw new NotUniqueException();});
        User user = userConverter.fromDto(dto);
        user.setActivationCode(authenticationService.getToken(new UserCredentialDto(dto.getEmail(), dto.getPassword())));
        User created = userRepository.save(user);
        return userConverter.toDto(created);
    }

    @Transactional
    public UserDto activate(String code) {
        User user = userRepository.findUserByActivationCode(code).orElseThrow(() -> new NotFoundException(User.class, code));
        user.setEnabled(true);
        user.setActivationCode(null);
        userRepository.save(user);
        UserDto dto = userConverter.toDto(user);
        dto.setJwt(code);
        return dto;
    }

    @Transactional(noRollbackFor = StateException.class)
    public Set<UserDto> deleteUsers(Set<Long> idSet) {
        idSet.forEach(userRepository::deleteById);
        if(authenticationService.logoutIfBlocked(idSet)) {
            throw new StateException("User was removed");
        }
        return getUsers();
    }

    @Transactional
    public User setActive(User user) {
        user.setLoginDate(new Date());
        user.setState(State.UNBLOCKED);
        return userRepository.save(user);
    }
}