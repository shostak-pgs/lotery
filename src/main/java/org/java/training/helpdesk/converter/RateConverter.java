package org.java.training.helpdesk.converter;

import org.java.training.helpdesk.dto.RateDto;
import org.java.training.helpdesk.entity.Rate;
import org.java.training.helpdesk.service.AuthenticationService;
import org.springframework.stereotype.Component;

@Component
public class RateConverter {

    private final AuthenticationService authenticationService;
    private final UserConverter userConverter;

    public RateConverter(AuthenticationService authenticationService, UserConverter userConverter) {
        this.authenticationService = authenticationService;
        this.userConverter = userConverter;
    }

    public RateDto toDto(final Rate entity) {
        return new RateDto(entity.getId(), userConverter.toDto(entity.getUser()), entity.getValue());
    }

    public Rate fromDto(final RateDto dto) {
        return new Rate(dto.getValue(), authenticationService.getAuthenticateUser());
    }
}