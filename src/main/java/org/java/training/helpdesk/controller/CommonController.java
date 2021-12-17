package org.java.training.helpdesk.controller;

import org.java.training.helpdesk.dto.UserDto;
import org.java.training.helpdesk.service.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping(value = "/common")
    public ResponseEntity<Map<String, Object>> getComments() {
        return ResponseEntity.ok(commonService.getFixedVales());
    }

    @GetMapping(value = "/run")
    public ResponseEntity<UserDto> runLotery() {
        return ResponseEntity.ok(commonService.runLatery());
    }
}