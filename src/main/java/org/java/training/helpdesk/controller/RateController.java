package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.RateDto;
import org.java.training.helpdesk.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final RateDto dto) {
        rateService.create(dto);
        return ResponseEntity.ok().build();
    }
}