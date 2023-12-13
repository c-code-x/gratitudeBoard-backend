package com.Studentlifr.GratitudeBoard.controller;

import com.Studentlifr.GratitudeBoard.dto.requests.MessageReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.services.MessageService;
import com.Studentlifr.GratitudeBoard.services.impl.TokenServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private final MessageService messageService;
    private final TokenServiceImpl tokenService;

    public UserController(MessageService messageService, TokenServiceImpl tokenService) {
        this.messageService = messageService;
        this.tokenService = tokenService;
    }

    @PostMapping("postMessage")
    public ResponseDTO sendMessage(Authentication authentication, @RequestBody @Valid MessageReqDTO messageReqDTO) {
        return messageService.addMessage( messageReqDTO, authentication.getName());
    }
}

