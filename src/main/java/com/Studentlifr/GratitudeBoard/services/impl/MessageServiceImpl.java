package com.Studentlifr.GratitudeBoard.services.impl;

import com.Studentlifr.GratitudeBoard.dao.MessageDAO;
import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dao.repository.MessageRepository;
import com.Studentlifr.GratitudeBoard.dto.requests.MessageReqDTO;
import com.Studentlifr.GratitudeBoard.dto.response.BasicResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.PublicMessagesResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.services.MessageService;
import com.Studentlifr.GratitudeBoard.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public ResponseDTO addMessage(MessageReqDTO messageDTO, String emailId) {
        UserDAO user = userService.getUserByEmailId(emailId);
        if (userService.isUserVerified(user.getEmailId())) {
            MessageDAO message = MessageDAO.builder()
                    .user(user)
                    .message(messageDTO.getMessage())
                    .isAnonymous(messageDTO.getIs_Anonymous())
                    .build();
            messageRepository.save(message);
            return new ResponseDTO(new BasicResDTO("Message added successfully", HttpStatus.OK));
        } else {
            return new ResponseDTO(new BasicResDTO("User not verified", HttpStatus.BAD_REQUEST));
        }
    }

    public GenericResDTO<List<PublicMessagesResDTO>> getAllMessages() {
        List<MessageDAO> messageDAOList = messageRepository.findAll();
        List<PublicMessagesResDTO> publicMessagesResDTOList = new ArrayList<>();

        for (MessageDAO messageDAO : messageDAOList) {
            PublicMessagesResDTO publicMessagesResDTO = new PublicMessagesResDTO();
            publicMessagesResDTO.setMessage(messageDAO.getMessage());

            if (!messageDAO.getIsAnonymous()) {
                publicMessagesResDTO.setName(messageDAO.getUser().getName());
            }

            publicMessagesResDTOList.add(publicMessagesResDTO);
        }

        return new GenericResDTO<>(publicMessagesResDTOList, new BasicResDTO("Messages fetched successfully", HttpStatus.OK));
    }
}