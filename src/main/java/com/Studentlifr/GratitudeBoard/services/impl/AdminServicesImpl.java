package com.Studentlifr.GratitudeBoard.services.impl;

import com.Studentlifr.GratitudeBoard.dao.MessageDAO;
import com.Studentlifr.GratitudeBoard.dao.UserDAO;
import com.Studentlifr.GratitudeBoard.dao.repository.MessageRepository;
import com.Studentlifr.GratitudeBoard.dao.repository.UserRepository;
import com.Studentlifr.GratitudeBoard.dto.response.*;
import com.Studentlifr.GratitudeBoard.enumerators.ROLES;
import com.Studentlifr.GratitudeBoard.services.AdminServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServicesImpl implements AdminServices {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    public AdminServicesImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }
    public GenericResDTO<List<AdminMessagesResDTO>> getAllMessages() {
        List<MessageDAO> messageDAOList = messageRepository.findAll();
        List<AdminMessagesResDTO> AdminMessagesResDTOList = new ArrayList<>();

        for (MessageDAO messageDAO : messageDAOList) {
            AdminMessagesResDTO adminMessagesResDTO = new AdminMessagesResDTO();
            adminMessagesResDTO.setMessage(messageDAO.getMessage());
            adminMessagesResDTO.setIs_Anonymous(messageDAO.getIsAnonymous());
            adminMessagesResDTO.setDate(messageDAO.getPostedOn());
            adminMessagesResDTO.setUser(messageDAO.getUser());
        }
        return new GenericResDTO<>(AdminMessagesResDTOList, new BasicResDTO("Messages fetched successfully", HttpStatus.OK));
    }
    public GenericResDTO<List<UserDAO>> getAllUsers() {
        List<UserDAO> userDAOList = userRepository.findAll();
        return new GenericResDTO<>(userDAOList, new BasicResDTO("Users fetched successfully", HttpStatus.OK));
    }
    public ResponseDTO deleteUserById(UUID id) {
        userRepository.deleteById(id);
        return new ResponseDTO(new BasicResDTO("User deleted successfully", HttpStatus.OK));
    }
    public ResponseDTO deleteMessageById(UUID id) {
        messageRepository.deleteById(id);
        return new ResponseDTO(new BasicResDTO("Message deleted successfully", HttpStatus.OK));
    }
    public ResponseDTO promoteUserById(UUID id) {
        UserDAO user = userRepository.findById(id).get();
        if(!user.getIsVerified()){
            return new ResponseDTO(new BasicResDTO("User is not verified", HttpStatus.BAD_REQUEST));
        }
        if(user.getRole() == ROLES.ADMIN) {
            return new ResponseDTO(new BasicResDTO("User is already an admin", HttpStatus.BAD_REQUEST));
        }
        user.setRole(ROLES.ADMIN);
        userRepository.save(user);
        return new ResponseDTO(new BasicResDTO("User promoted successfully", HttpStatus.OK));
    }
    public ResponseDTO demoteUserById(UUID id) {
        UserDAO user = userRepository.findById(id).get();
        if(user.getRole() == ROLES.USER) {
            return new ResponseDTO(new BasicResDTO("User is already a user", HttpStatus.BAD_REQUEST));
        }
        user.setRole(ROLES.USER);
        userRepository.save(user);
        return new ResponseDTO(new BasicResDTO("User demoted successfully", HttpStatus.OK));
    }
}
