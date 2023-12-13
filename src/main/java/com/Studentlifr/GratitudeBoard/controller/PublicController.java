package com.Studentlifr.GratitudeBoard.controller;

import com.Studentlifr.GratitudeBoard.dao.MessageDAO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.PublicMessagesResDTO;
import com.Studentlifr.GratitudeBoard.services.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {
    private final MessageService messageService;
    public PublicController(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping("messages")
    public GenericResDTO<List<PublicMessagesResDTO>> getAllMessages(){
        return messageService.getAllMessages();
    }
}
